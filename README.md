# Chamados-IFRN

Projeto didático para demonstrar testes de carga e estresse com [k6](https://grafana.com/docs/k6/latest/). Os exemplos estão em `performance_test/` e usam a API local em `http://localhost:8080`.

## Pré-requisitos

- Docker com Docker Compose, para executar a aplicação e o PostgreSQL.
- k6 instalado na máquina que executará os testes. Consulte as [instruções oficiais de instalação](https://grafana.com/docs/k6/latest/set-up/install-k6/).
- Para executar a aplicação fora do Docker: Java 21 e Maven (ou o wrapper `./mvnw` incluído no projeto).

## 1. Configurar e iniciar a aplicação

Na raiz do projeto, crie um arquivo `.env` com as configurações locais do banco:

```dotenv
DATASOURCE_NAME=ifrn_database
DATASOURCE_USERNAME=ifrn
DATASOURCE_PASSWORD=ifrn
DATASOURCE_URL=jdbc:postgresql://ifrn_datasource:5432/ifrn_database
```

Esses valores são apenas um exemplo para desenvolvimento local. Ajuste-os se necessário e não coloque credenciais reais ou de produção no arquivo.

Inicie a aplicação e o banco:

```bash
docker compose up --build
```

O Compose aguarda o banco ficar saudável antes de iniciar a aplicação. Na primeira execução, a imagem da aplicação é compilada e as migrações do banco são aplicadas. Mantenha esse terminal aberto durante os testes.

Confirme que a API está respondendo em outro terminal:

```bash
curl http://localhost:8080/server
```

A resposta esperada é `server is running`. A rota `/server` não exige autenticação e é o alvo principal dos exemplos.

## 2. Executar os exemplos

Com o k6 instalado localmente e a aplicação em execução, rode os scripts a partir da raiz do projeto:

```bash
k6 run performance_test/1_introducao.js
```

Cada script pode ser executado de forma independente:

| Script | O que demonstra |
| --- | --- |
| `1_introducao.js` | Requisição HTTP e exibição do status. |
| `2_check.js` | Verificação da resposta com `check`. Usa `/server/50`, que retorna HTTP 200 ou 400 aleatoriamente; portanto, o check pode falhar intencionalmente. |
| `3_corpo.js` | Verificações do status e do conteúdo da resposta. |
| `4_threshold.js` | Threshold: 95% das requisições devem terminar em menos de 500 ms. |
| `5_aumentar_requisicoes.js` | 100 iterações e threshold de 200 ms para o percentil 95. |
| `6_usuarios_virtuais.js` | 10 usuários virtuais e 100 iterações. |
| `7_cenarios.js` | Cenário de estresse progressivo, chegando a 150 usuários virtuais. |
| `8_spike__test.js` | Teste de pico autenticado, chegando a 200 usuários virtuais. |

Por exemplo, para executar o teste de estresse:

```bash
k6 run performance_test/7_cenarios.js
```

Ao final, o k6 exibe métricas como duração das requisições, taxa de falhas, checks e resultado dos thresholds. Um threshold não atendido faz a execução terminar com falha, mesmo que o script tenha concluído.

### Teste de pico autenticado

O script `8_spike__test.js` faz login em `/auth/login` e consulta `/users/me`. Configure uma conta válida da aplicação antes de executá-lo.

macOS/Linux:

```bash
K6_EMAIL="usuario@example.com" K6_PASSWORD="sua-senha" \
  k6 run performance_test/8_spike__test.js
```

PowerShell:

```powershell
$env:K6_EMAIL = "usuario@example.com"
$env:K6_PASSWORD = "sua-senha"
k6 run performance_test/8_spike__test.js
```

## 3. Encerrar

Pare a aplicação com `Ctrl+C` no terminal do Compose. Para remover os containers:

```bash
docker compose down
```

Esse comando preserva os dados no volume do PostgreSQL. Para também apagar o banco local e seus dados, use `docker compose down -v`.

## Cuidados ao testar

- Execute testes de carga somente em sistemas que você controla ou tem autorização para testar.
- Comece pelos scripts com carga menor e observe o consumo de CPU, memória e banco antes de executar os testes de estresse ou pico.
- Os exemplos têm duração e quantidade de usuários definidos no próprio código. Ajuste-os com cuidado para não sobrecarregar a máquina.

## Material de apoio

O diretório `docs/` contém o material [K6: do primeiro script ao teste de performance](docs/K6-do-primeiro-script-ao-teste-de-performance.pdf).
