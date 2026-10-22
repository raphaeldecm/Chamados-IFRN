import http from 'k6/http';
import { check } from 'k6';

const baseUrl = 'http://localhost:8080';

export function setup() {
    if (!__ENV.K6_EMAIL || !__ENV.K6_PASSWORD) {
        throw new Error('Defina K6_EMAIL e K6_PASSWORD para executar o teste 8.');
    }

    const response = http.post(`${baseUrl}/auth/login`, JSON.stringify({
        email: __ENV.K6_EMAIL,
        password: __ENV.K6_PASSWORD,
    }), {
        headers: { 'Content-Type': 'application/json' },
    });

    const loginSucceeded = check(response, {
        'login retornou HTTP 200': (r) => r.status === 200,
        'login retornou accessToken': (r) => {
            try {
                return Boolean(JSON.parse(r.body).accessToken);
            } catch (_) {
                return false;
            }
        },
    });

    if (!loginSucceeded) {
        throw new Error(`Falha no login do teste 8: HTTP ${response.status} - ${String(response.body).slice(0, 500)}`);
    }

    return JSON.parse(response.body).accessToken;
}

export const options = {
    scenarios: {
        spike_test: {
            executor: 'ramping-vus',
            startVUs: 5,
            stages: [
                { duration: '10s', target: 5 },   // Aquecimento: 5 usuários
                { duration: '5s', target: 100 },  // Pico repentino: 100 usuários
                { duration: '30s', target: 100 }, // Mantém o pico
                { duration: '20s', target: 200 }, // Aumenta para 200 usuários
                { duration: '10s', target: 0 },   // Reduz a carga
                { duration: '5s', target: 0 },
            ],
        }
    },

    thresholds: {
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<500'],
        checks: ['rate>0.99'],
    },
};

export default function (accessToken) {
    const url = `${baseUrl}/users/me`;

    const params = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
            'Content-Type': 'application/json',
        }
    };

    const response = http.get(url, params);

    if (__VU === 1 && __ITER < 5) {
        console.log(`Status HTTP: ${response.status}`);
        console.log(`Erro: ${response.error || 'Nenhum erro de transporte informado'}`);
        console.log(`Corpo: ${String(response.body ?? '<vazio ou nulo>').slice(0, 1000)}`);
    }


    check(response, {
        'status HTTP é 200': (r) => r.status === 200,
        'resposta não está vazia': (r) => r.body !== null && r.body.length > 0,
    });
}
