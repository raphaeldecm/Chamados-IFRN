import http from 'k6/http';

export default function () {
    const resposta = http.get('http://localhost:8080/server');

    console.log(`Status HTTP: ${resposta.status}`);
}