import http from 'k6/http';
import { check } from 'k6';

export default function () {
    const resposta = http.get('http://localhost:8080/server');

    check(resposta, {
        'status HTTP é 200': (r) => r.status === 200,
    });
}