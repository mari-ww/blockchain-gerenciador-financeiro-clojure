# Blockchain para Gerenciador Financeiro em Clojure

Este projeto combina o desenvolvimento de um gerenciador de finanças pessoais com o uso de blockchain para registrar e monitorar transações de forma imutável.

## Funcionalidades Principais
- **Gerenciador de Finanças Pessoais:**
  - Cadastrar e exibir transações financeiras.
  - Interface de usuário para facilitar o uso.
- **Blockchain:**
  - Criar e gerenciar uma cadeia de blocos.
  - Minerar novos blocos utilizando Prova de Trabalho (Proof-of-Work).
  - API para registrar transações e retornar informações sobre os blocos.

## Estrutura de Dados Blockchain
Cada bloco possui:
- **Número do Bloco**: Identificador sequencial.
- **Nonce**: Número gerado para validação do hash.
- **Dados**: Lista de transações.
- **Hash Anterior**: Referência ao hash do bloco anterior.
- **Hash**: Chave gerada pelo SHA-256.

## Pré-requisitos
- **Linguagem**: Clojure.
- **Requisitos**: Estudo prévio dos capítulos 9 a 13 do livro *Programação Funcional: Uma Introdução em Clojure*.
- **Simuladores e Ferramentas**:
  - Gerador de hashes (exemplo: [Gerador SHA-256 online](https://www.browserling.com/tools/sha256)).
  - Simulador de blockchain (opcional).

## Instalação e Execução
1. Certifique-se de ter o [Leiningen](https://leiningen.org/) instalado.
2. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/blockchain-gerenciador-financeiro-clojure.git
3. Navegue até o diretório:
   cd financeiro
4. Execute o projeto:
   lein run

## Instalação e Execução
A interface apresenta opções para:

1. Cadastrar transações no gerenciador de finanças.
2. Exibir transações e blocos da blockchain.
