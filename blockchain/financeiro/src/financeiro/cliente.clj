
(ns financeiro.cliente
  (:require [financeiro.db :as db]
            [financeiro.blockchain :as blockchain]))

(defn menu []
  (println "1. Cadastrar Transação")
  (println "2. Exibir Transações")
  (println "3. Registrar Transação no Blockchain")
  (println "4. Exibir Blockchain")
  (println "5. Sair"))

(defn cadastrar-transacao []
  (let [id (atom (db/proximo-id))]
    (loop []
      (println "Digite o valor da transação:")
      (let [valor (read-line)
            tipo (do (println "Digite o tipo da transação (receita/despesa):")
                     (read-line))]
        (db/adicionar-transacao {:valor (read-string valor) :tipo tipo :id @id})
        (println "Transação cadastrada com sucesso!"))
      (println "Deseja cadastrar outra transação? (s/n)")
      (let [resposta (read-line)]
        (when (= resposta "s")
          (recur))))))

(defn exibir-transacoes []
  (let [transacoes (db/transacoes)]
    (println "Transações cadastradas:")
    (doseq [transacao transacoes]
      (println transacao))))

(defn registrar-transacao []
  (println "Digite o ID da transação para registrar no blockchain:")
  (let [id (read-line)
        transacoes (filter #(= (:id %) (read-string id)) (db/transacoes))]
    (if (seq transacoes)
      (do
        (blockchain/adicionar-bloco transacoes)
        (println "Transação registrada no blockchain com sucesso!"))
      (println "Transação não encontrada!"))))

(defn exibir-blockchain []
  (let [blocos (blockchain/obter-blocos)]
    (println "Blockchain:")
    (doseq [bloco blocos]
      (println "Número: " (:numero bloco))
      (println "Nonce: " (:nonce bloco))
      (println "Dados: " (:dados bloco))
      (println "Prev-hash: " (:prev-hash bloco))
      (println "Hash: " (:hash bloco))
      (println "--------------------------------"))))

(defn -main []
  (loop []
    (menu)
    (let [escolha (read-line)]
      (case escolha
        "1" (do (cadastrar-transacao) (recur))
        "2" (do (exibir-transacoes) (recur))
        "3" (do (registrar-transacao) (recur))
        "4" (do (exibir-blockchain) (recur))
        "5" (println "Saindo...")
        (do (println "Opção inválida!") (recur))))))
