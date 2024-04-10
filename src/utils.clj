(ns utils
  (:require
   [clojure.pprint :refer [print-table]]))

(defn has-todo?
  [v]
  (-> v meta :todo))

(defn fn-has-todo?
  [[_ v]]
  (and (ifn? v) (has-todo? v)))

(defn create-todo-map
  [[k v]]
  (hash-map "Todo" (:todo (meta v)), "Function" (name k)))

(defn get-todos
  {:todo "Rename the variables"}
  []
  (->> (ns-publics *ns*)
       (filter fn-has-todo?)
       (map create-todo-map)
       (print-table)))