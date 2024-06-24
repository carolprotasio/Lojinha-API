# Projeto Lojinha-API

O projeto Lojinha-API é uma plataforma desenvolvida para testes automatizados de APIs REST, utilizando Rest Assured e JUnit, focando em práticas de qualidade de software.
Testes em API REST são essenciais para garantir que as interfaces de programação estejam funcionando corretamente e retornando os resultados esperados. Utilizando o Rest Assured, é possível realizar requisições HTTP, validar respostas e automatizar testes de integração.

![Lojinha](https://github.com/carolprotasio/Lojinha-API/blob/master/src/main/resources/Assets/lojinha.png)

## Tecnologias Utilizadas

- **Java**: Linguagem de programação robusta e amplamente utilizada na indústria de desenvolvimento de software.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências em projetos Java.
- **Java Faker**: Biblioteca para geração de dados fictícios de forma simplificada.
- **Git**: Sistema de controle de versão distribuído para controle de mudanças no código fonte.
- **Jackson Databind**: Biblioteca para conversão de objetos Java em JSON (e vice-versa).
- **JUnit Jupiter**: Framework para escrever e executar testes unitários em Java.
- **Rest Assured**: Framework Java para testes automatizados de APIs REST.
- **IDE IntelliJ**: Ambiente de desenvolvimento integrado amplamente utilizado para desenvolvimento Java.
  

## Funcionalidades Testadas

O projeto Lojinha-API testa as seguintes funcionalidades principais:

* Modulo Usuário - Validar os verbos HTTP do usuário - CRUD

  | Funcionalidade | Descrição |
    | -------------- | --------- |
  | `Usuário ` | `Validar os verbos HTTP do usuário - CRUD`|  
  | POST - Validar obtenção do token  | Teste para obtenção do token com sucesso. | 
  | POST - Validar adicionar novo usuário | Teste para verificar se é possível adicionar um novo usuário com sucesso. |
  | DELETE - Limpar todos os dados do usuário | Teste para limpar todos os dados de usuários existentes no sistema. |


* Modulo Usuário  - Validar Testes exploratórios

  | Funcionalidade | Descrição |
    | -------------- | --------- |
  | `Usuário ` | `Validar Testes exploratórios ` |
  | Validar cadastro de usuário com campos obrigatórios faltando | Teste para garantir que o sistema rejeita o cadastro de usuário quando campos obrigatórios estão ausentes. |
  | Validar cadastro de usuário com email já existente | Teste para verificar se o sistema impede o cadastro de um usuário com email já registrado. |

* Modulo Usuário  - Validar injeção de SQL no cadastro de usuário

  | Funcionalidade | Descrição |
    | -------------- | --------- |
  | `Usuário` | `Validar injeção de SQL no cadastro de usuário ` |
  | Teste com Subconsultas - Validar injeção de SQL com subconsulta | Teste para detectar e prevenir vulnerabilidades de injeção de SQL utilizando subconsultas. |
  | Teste com Funções SQL - Validar injeção de SQL com funções SQL | Teste para identificar e corrigir potenciais falhas de segurança com injeção de SQL através de funções SQL. |
  | Teste com União de Consultas - Validar injeção de SQL com UNION | Teste para assegurar que o sistema esteja protegido contra injeção de SQL usando a técnica de UNION.

* Modulo Produto - Validar os verbos HTTP do produto - CRUD

  | Funcionalidade | Descrição |
    | -------------- | --------- |
  | `Produto` | `Validar os verbos HTTP do produto - CRUD ` |
  | POST - Validar adicionar novo Produto | Teste para verificar se é possível adicionar um novo produto com sucesso. |
  | GET - Validar busca de um dos produtos dos usuário | Teste para verificar a busca de um produto específico do usuário. |
  | GET - Validar busca de todos os produtos do usuário | Teste para buscar todos os produtos cadastrados pelo usuário. |
  | PUT - Validar alterar informações de um produto | Teste para modificar informações de um produto existente. |
  | DELETE - Validar remover um produto | Teste para deletar um produto do sistema. |

* Modulo Produto - Testes de API Rest do módulo de Produtos - Valores Limites

  | Funcionalidade | Descrição |
    | -------------- | --------- |
  | `Produto` | ` Testes de API Rest do módulo de Produtos - Valores Limites` |
  | Valor Limite - Validar que valor 0(zero) do produto não é permitido | Teste para validar que o sistema rejeita produtos com preço zero. |
  | Valor Limite - Validar que valor maior que sete mil do produto não é permitido | Teste para verificar se o sistema limita o preço máximo de produtos. |
  | Valor Limite - Validar que valor 0.01 do produto é permitido | Teste para assegurar que produtos com preço mínimo são aceitos pelo sistema. |
  | Valor Limite - Validar que valor 7000 do produto é permitido | Teste para confirmar que produtos com preço máximo permitido são aceitos. |

* Modulo Produto - SQL Injection - Testar vulnerabilidade de injeção de SQL no modulo produto

  | Funcionalidade | Descrição |
    | -------------- | --------- |
  | `Produto` | `SQL Injection - Testar vulnerabilidade de injeção de SQL no modulo produto` |
  | GET - Testar SQL Injection no endpoint de busca de produto por ID | Teste para detectar e mitigar possíveis vulnerabilidades de injeção de SQL no endpoint de busca por ID. |
  | GET - Testar Injeção de SQL em strings | Teste para identificar e corrigir vulnerabilidades de injeção de SQL em parâmetros de texto. |
  | GET - Testar Injeção de SQL em parâmetros numéricos | Teste para validar a segurança contra injeção de SQL em parâmetros numéricos. |
  | GET - Testar SQL Injection em outro endpoint | Teste adicional para garantir a proteção contra injeção de SQL em diferentes endpoints.

* Modulo Componente - Validar os verbos HTTP dos Componentes - CRUD

  | Funcionalidade | Descrição |
    | -------------- | --------- |
  | `Componente` | `Validar os verbos HTTP dos Componentes - CRUD` |
  | POST - Validar adicionar novo componente ao produto | Teste para verificar se é possível adicionar um novo componente a um produto com sucesso. |
  | GET - Validar buscar dados dos componentes de um produto | Teste para buscar informações dos componentes associados a um produto específico. |
  | GET - Validar buscar UM componente de um produto | Teste para verificar a busca de um componente específico de um produto. |
  | PUT - Validar alterar informações de um componente de produto | Teste para modificar informações de um componente de produto existente. |
  | DELETE - Validar remover um componente do produto | Teste para deletar um componente de um produto do sistema. |

* Resultado dos Testes realizados:

  ![Resultado dos Testes](https://github.com/carolprotasio/Lojinha-API/blob/master/src/main/resources/Assets/resultado-testes.png)

## Documentação API com Swagger

A documentação da API foi elaborada utilizando o Swagger, uma ferramenta amplamente reconhecida para documentação de APIs. O Swagger oferece uma interface interativa que permite visualizar e testar as operações da API diretamente no navegador.

![Documentação Swagger da Lojinha-API](https://github.com/carolprotasio/Lojinha-API/blob/master/src/main/resources/Assets/swagger.png)






## Como Executar o Projeto

### Pré-requisitos

- JDK Java instalado
- Maven instalado

### Clone do Projeto

```bash
git clone https://github.com/seu-usuario/lojinha-api.git
cd lojinha-api
```

### ## Conclusão do Projeto

Este projeto foi desenvolvido como parte da Mentoria com Julio de Lima, proporcionando uma experiência valiosa no desenvolvimento de testes automatizados utilizando JUnit e Rest Assured. Os testes realizados abrangeram diversas funcionalidades da plataforma Lojinha-API, incluindo validações de usuários, produtos e componentes, bem como a segurança contra vulnerabilidades de injeção de SQL.

A prática intensiva de testes automatizados contribuiu significativamente para o aprimoramento das habilidades em garantia da qualidade de software, promovendo um desenvolvimento mais robusto e confiável. Este projeto não apenas consolidou o conhecimento em testes de API REST, mas também proporcionou um ambiente ideal para aprender e aplicar boas práticas de desenvolvimento orientado a testes.


