(ns financeiro.db
  (:require [financeiro.blockchain :as blockchain]))

(def registros
  (atom []))

(defn saldo []
  (reduce (fn [acumulado transacao]
            (let [valor (:valor transacao)]
              (if (= (:tipo transacao) "despesa")
                (- acumulado valor)
                (+ acumulado valor))))
          0
          @registros))

(defn limpar []
  (reset! registros []))

(defn transacoes []
  @registros)

(defn proximo-id []
  (if (empty? @registros)
    1
    (inc (:id (last @registros)))))

(defn adicionar-transacao [transacao]
  (let [transacao-com-id (merge transacao {:id (:id transacao)})]
    (swap! registros conj transacao-com-id)
    transacao-com-id))

(defn obter-transacao [id]
  (some #(when (= (:id %) id) %) @registros))

(defn registrar-transacao [transacao]
  (blockchain/adicionar-bloco [transacao])
  transacao)
