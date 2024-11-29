(ns financeiro.blockchain
  (:require [clojure.string :as str])
  (:import [java.security MessageDigest]))

(defn sha256 [string]
  (let [digest (.digest (MessageDigest/getInstance "SHA-256") (.getBytes string "UTF-8"))]
    (apply str (map (partial format "%02x") digest))))

(def blockchain (atom []))

(defn- hash-valido? [hash]
  (str/starts-with? hash "0000"))

(defn criar-bloco [dados prev-hash]
  (loop [nonce 0]
    (let [bloco {:numero (inc (count @blockchain))
                 :dados dados
                 :prev-hash prev-hash
                 :nonce nonce
                 :hash (sha256 (str (inc (count @blockchain)) dados prev-hash nonce))}]
      (if (hash-valido? (:hash bloco))
        bloco
        (recur (inc nonce))))))

(defn adicionar-bloco [dados]
  (let [prev-bloco (last @blockchain)
        prev-hash (if (nil? prev-bloco)
                    (apply str (repeat 64 "0"))
                    (:hash prev-bloco))
        novos-dados (if (nil? prev-bloco)
                      dados
                      (concat (:dados prev-bloco) dados))
        bloco (criar-bloco novos-dados prev-hash)]
    (swap! blockchain conj bloco)))

(defn obter-blocos []
  @blockchain)
