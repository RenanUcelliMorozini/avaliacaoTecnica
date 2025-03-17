## 🗳️ API de Votação  

### 📖 Descrição  
Esta API REST permite a criação de pautas, abertura de sessões de votação e  registro de votos e contabilização dos resultados recebendo junta ao voto dados informados em tela com campos de texto, numérico e data.  

### 🛠️ Tecnologias Utilizadas  
- ⚡ **Spring Boot** – Framework para desenvolvimento rápido em Java  
- 🗄️ **H2 Database** – Banco de dados em memória e persistente  
- 🏗️ **Maven** – Gerenciador de dependências  
- 📦 **Hibernate** – ORM para manipulação do banco de dados  

### 📌 Funcionalidades  
✔️ **Cadastrar Pauta**  
✔️ **Abrir Sessão de Votação** (tempo customizável ou 1 minuto por padrão)  
✔️ **Receber Votos** (Apenas 'Sim' ou 'Não'; um voto por associado por pauta)  
✔️ **Contabilizar e Consultar Resultados**  
✔️ **Persistir dados informados**

### 📌 Exemplo de Requisição - Criar Pauta  

**Requisição:**  
```http
POST http://localhost:8080/pautas?tipo=PAUTAS&titulo=FORMULARIO
```
A API recebe o tito e o título para definir qual será a nova pauta criada, retornando assim quando aplicado o método GET:

```[
    {
        "tipo": "PAUTAS",
        "titulo": "FORMULARIO",
        "sessoes": [],
        "botaoOk": {
            "texto": "Ação",
            "url": "softdesgin.com/ACAO"
        },
        "botaoCancelar": {
            "texto": "Cancelar",
            "url": "softdesgin.com"
        }
    }
]
```

### 📌 Exemplo de Requisição - Criar Sessão de votação

Após ter uma Pauto o usuário pode então criar uma sessão de votação.

**Requisição:**

```
http://localhost:8080/sessoes/abrir?pautaId=1&tipo=INPUT_TEXTO&duracaoMinutos=2&valorTexto=Teste Texto

http://localhost:8080/sessoes/abrir?pautaId=1&tipo=INPUT_NUMERO&valorNumerico=5

http://localhost:8080/sessoes/abrir?pautaId=1&tipo=INPUT_DATA&valorData=24/02/2025
```
No ato da criação da sessão o sistema espera receber como parâmetro:

- **Long pautaId** - Id da pauta criada;
- **DominioTipoCampo tipo** - Domínio padrão que determina o campo informado;
- **Long duracaoMinutos** - Padrão de 1 minuto, porém ajustável de acordo com valor enviado;
- **String valorTexto** - Valor informado no input texto em tela;
- **Integer valorNumerico** - Valor informado no input número em tela;
- **LocalDate valorData** - Valor informado no input data em tela.

Após o abertura da sessão é possível então consultar as sessões assim como ao consultar a pauta, nós já teremos a sessão vinculada:

**Requisição:**
```
http://localhost:8080/sessoes

http://localhost:8080/pautas
```

**Retorno:**
```
[
    {
        "tipo": "PAUTAS",
        "titulo": "FORMULARIO",
        "sessoes": [
            {
                "tipo": "INPUT_TEXTO",
                "votos": [],
                "titulo": "Campo de Texto",
                "valorTexto": "Teste Texto",
                "id": "idCampoTexto"
            },
            {
                "tipo": "INPUT_NUMERO",
                "votos": [],
                "titulo": "Campo Numérico",
                "valorNumerico": 5,
                "id": "idCampoNumerico"
            }
        ],
        "botaoOk": {
            "texto": "Ação",
            "url": "softdesgin.com/ACAO"
        },
        "botaoCancelar": {
            "texto": "Cancelar",
            "url": "softdesgin.com"
        }
    }
]
```

### 📌 Exemplo de Requisição - Registrar Votos

Por fim, após o a abertura da sessão é possível então registrar o voto(uma única vez por associado)


**Requisição:**
```
http://localhost:8080/votos?sessaoId=1&associadoId=1&voto=true
```

No ato da criação da sessão o sistema espera receber como parâmetro:

- **Long sessaoId** - Id da da sessão criada;
- **Long associadoId** - Id do associado que está votando;
- **boolean voto** - voto do associado;

Após informar seu voto o sistema retorna toda a estrutura ao realizar a consulta da pauta:
```
http://localhost:8080/pautas
```

```
[
    {
        "tipo": "PAUTAS",
        "titulo": "FORMULARIO",
        "sessoes": [
            {
                "tipo": "INPUT_TEXTO",
                "votos": [
                    {
                        "id": 1,
                        "associadoId": 1,
                        "voto": true
                    }
                ],
                "titulo": "Campo de Texto",
                "valorTexto": "Teste Texto",
                "id": "idCampoTexto"
            },
            {
                "tipo": "INPUT_NUMERO",
                "votos": [],
                "titulo": "Campo Numérico",
                "valorNumerico": 5,
                "id": "idCampoNumerico"
            },
            {
                "tipo": "INPUT_DATA",
                "votos": [],
                "titulo": "Campo de Data",
                "valorData": "2025-02-24",
                "id": "idCampoData"
            }
        ],
        "botaoOk": {
            "texto": "Ação",
            "url": "softdesgin.com/ACAO"
        },
        "botaoCancelar": {
            "texto": "Cancelar",
            "url": "softdesgin.com"
        }
    }
]
```