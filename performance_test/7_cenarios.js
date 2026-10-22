import http from 'k6/http'
import { check } from 'k6';

export const options = {
    scenarios: {
        stress_test: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                {duration: '10s', target: 5},
                {duration: '5s', target: 10},
                {duration: '5s', target: 10},
                {duration: '10s', target: 100},
                {duration: '10s', target: 150}
            ]
        }
    },
    thresholds: {
        http_req_duration: ['p(95)<500'],
        http_req_failed: ['rate<0.01'],
        checks: ['rate>0.99'],
    },
};

export default function () {
    const resposta = http.get('http://localhost:8080/server');

    check(resposta, {
        'status HTTP é 200': (r) => r.status === 200,
        'Corpo possui mensagem esperada': (r) => r.body.includes("server is running")
    });
}