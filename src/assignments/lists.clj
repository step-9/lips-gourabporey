(ns assignments.lists
  (:require
   [utils :refer [get-todos]]))

(defn map'
  "Implement a non-lazy version of map that accepts a
  mapper function and several collections. The output
  should be consistent with clojure.core/map"
  {:level        :medium
   :use          '[loop recur]
   :dont-use     '[map]}
  [f & colls])

(defn filter'
  "Implement a non-lazy version of filter that accepts a
  predicate function and a collection. The output
  should be consistent with clojure.core/filter"
  {:level        :easy
   :use          '[loop recur]
   :dont-use     '[filter]
   :todo         "Refactor nested ifs"}
  [pred coll]
  (loop [selected-elems []
         coll coll]
    (if-let [elem (first coll)]
      (recur
       (if (pred elem)
         (conj selected-elems elem)
         selected-elems)
       (rest coll))
      selected-elems)))

(defn reduce'
  "Implement your own multi-arity version of reduce
  that accepts a predicate function and a collection.
  The output should be consistent with clojure.core/reduce"
  {:level        :medium
   :use          '[loop recur]
   :dont-use     '[reduce]}
  ([f coll])
  ([f init coll]))

(defn count'
  "Implement your own version of count that counts the
  number of elements in a given sequence"
  {:level        :easy
   :use          '[loop recur]
   :dont-use     '[count]}
  [coll]
  (loop [len 0
         coll coll]
    (if (empty? coll)
      len
      (recur (inc len) (rest coll)))))

(defn reverse'
  "Implement your own version of reverse that reverses a coll.
  Returns nil if coll provided is not a sequence"
  {:level        :easy
   :use          '[reduce conj seqable? when]
   :dont-use     '[reverse]}
  [coll]
  (when (seqable? coll)
    (->> coll
         (reduce #(conj %1 %2) '()))))

(defn every?'
  "Implement your own version of every? that checks if every
  element of a coll satisfies the given predicate"
  {:level        :easy
   :use          '[loop recur and]
   :dont-use     '[every?]}
  [pred coll]
  #_(loop [[fst & more :as coll] coll]
      (cond
        (empty? coll) true
        (pred fst) (and true (recur more))
        :else false))
  (loop [[fst & more :as coll] coll]
    (if (empty? coll)
      true
      (and (pred fst)
           (recur more)))))

(defn some?'
  "Implement your own version of some that checks if at least one
  element of a coll satisfies the given predicate. Always return
  a boolean. The original clojure.core/some returns a nil when
  no element satisfies the given predicate"
  {:level        :easy
   :use          '[loop recur or]
   :dont-use     '[some]}
  [pred coll])

(defn ascending?
  "Verify if every element is greater than or equal to its predecessor"
  {:level        :easy
   :use          '[partition every? partial apply <=]
   :dont-use     '[loop recur]}
  [coll]
  (->> coll
       (partition 2 1)
       (every? (partial apply <=))))

(defn distinct'
  "Implement your own lazy sequence version of distinct which returns
  a collection with duplicates eliminated. Might have to implement another
  function, or use a letfn"
  {:level        :medium
   :use          '[lazy-seq set conj let :optionally letfn]
   :dont-use     '[loop recur distinct]}
  [coll])

(defn dedupe'
  "Implement your own lazy sequence version of dedupe which returns
  a collection with consecutive duplicates eliminated (like the uniq command).
  Might have to implement another function, or use a letfn"
  {:level        :medium
   :use          '[lazy-seq conj let :optionally letfn]
   :dont-use     '[loop recur dedupe]}
  [coll])

(defn sum-of-adjacent-digits
  "Given a collection, returns a map of the sum of adjacent digits.
  [a b c] => [a+b b+c]"
  {:level        :medium
   :use          '[map + rest]
   :dont-use     '[loop recur partition]}
  [coll]
  (map + coll (rest coll)))

(defn max-three-digit-sequence
  "Given a collection of numbers, find a three digit sequence that
  yields the max sum. If the collection has fewer than 3 elements,
  returns the collection itself.
  [1 2 -1 2 0] => [2 -1 2]"
  {:level        :medium
   :use          '[map next nnext max-key partial apply + if ->>]
   :dont-use     '[loop recur partition]}
  [coll])

;; transpose is a def. Not a defn.
(def
  ^{:level        :easy
    :dont-use     '[loop recur for nth get]}
  transpose
  "Transposes a given matrix.
  [[a b] [c d]] => [[a c] [b d]].
  Note this is a def. Not a defn.
  Return a vector of vectors, not list of vectors or vectors of lists"
  (partial apply map list))

(defn difference
  "Given two collections, returns only the elements that are present
  in the second coll but not the first"
  {:level        :easy
   :use          '[remove set]
   :dont-use     '[loop recur if]}
  [coll1 coll2]
  (remove #((set coll2) %) coll1))

(defn union
  "Given two collections, returns a new collection with elements from the second
  collection added to the first collection if they are missing in the first
  collection to begin with. Return a list, not a set. It also doesn't matter
  if elements repeat."
  {:level        :easy
   :use          '[remove into set ->>]}
  [coll1 coll2])

;; points-around-origin is a def not a defn
(def
  ^{:level        :easy
    :use          '[for]
    :dont-use     '[hardcoded-values map filter]}
  points-around-origin
  "Calculate all the points around the origin
  [-1 -1] [0 -1] [1 -1] etc. There should be 8 points
  Note this is a def, not a defn")

(defn cross-product
  "Given two sequences, generate every combination in the sequence
  until two elements are equal
  [1 2 3] [4 3 5] =>
  [[1 4] [1 3] [1 5] [2 4] [2 3] [2 5] [3 4]]"
  {:level        :easy
   :use          '[for]}
  [seq1 seq2])

(defn double-up
  "Given a collection, return a new collection that contains
  each element repeated twice"
  {:level        :easy
   :use          '[mapcat partial repeat :optionally vector]}
  [coll])

(defn third-or-fifth
  "Given a collection return a new collection that contains
  elements whose index is either divisible by three or five"
  {:level        :easy
   :use          '[keep-indexed when :optionally map-indexed filter]}
  [coll])

(defn sqr-of-the-first
  "Given a collection, return a new collection that contains the
  same number of elements as the given collection all of which
  are the square of the first element
  [4 5 6] => [16 16 16]"
  {:level        :easy
   :use          '[map constantly let]}
  [coll])

(defn russian-dolls
  "Given a collection and a number, wrap each element in a nested vector
  with a nesting factor of the number provided.
  [1 2 3] 3 => [[[1]] [[2]] [[3]]]"
  {:level        :medium
   :use          '[iterate mapv partial vector drop first ->>]
   :dont-use     '[for loop recur reduce]}
  [coll nesting-factor])

(defn split-comb
  "Given a collection, return a new sequence where the first
  half of the sequence is interleaved with the second half.
  If the given collection has an odd number of elements, then
  preserve the last element of the original collection
  [1 2 3 4 5] => [1 3 2 4 5]"
  {:level        :easy
   :use          '[interleave split-at if rem concat take-last]
   :dont-use     '[loop recur map-indexed take drop]}
  [coll])

(defn muted-thirds
  "Given a sequence of numbers, make every third element
  0 while preserving the other elements
  [1 2 8 4 15 2 7] => [1 2 0 4 15 0 7]"
  {:level        :easy
   :use          '[map cycle]
   :dont-use     '[loop recur map-indexed take take-nth]}
  [coll])

(defn palindrome?
  "Implement a recursive palindrome check of any given sequence"
  {:level        :easy
   :use          '[empty? loop recur butlast rest]
   :dont-use     '[reverse]}
  [coll])

(defn index-of
  "index-of takes a sequence and an element and finds the index
  of the element in the given sequence. Returns -1 if element
  is not found"
  {:level        :easy
   :use          '[loop recur rest]
   :dont-use     '[.indexOf memfn]}
  [coll n]
  (loop [index 0
         [fst & coll] coll]
    (cond
      (= n fst) index
      (nil? coll) -1
      :else (recur (inc index) coll))))

(defn validate-sudoku-grid
  "Given a 9 by 9 sudoku grid, validate it."
  {:level        :hard}
  [grid])

(defn max-of-pairs
  "Returns the maximum of each pair of elements in coll"
  {:level :easy
   :use   '[map max rest]
   :alternates '[repeat partition]}
  [coll]
  (if (<= (count coll) 1)
    coll
    (map max (rest coll) coll)))

(defn filter-by-index
  "Returns elements of coll at even indices"
  {:level :easy
   :use   '[keep-indexed]}
  [pred coll]
  (keep-indexed #(when (pred %1) %2) coll))

(defn collatz-fn
  [n]
  (if (even? n)
    (/ n 2)
    (+ 1 (* 3 n))))

(defn take-until
  {:todo "Simplify the loop recur"}
  [pred coll]
  (loop [selected-items []
         [first & other-elems :as coll] coll]
    (cond
      (empty? coll) selected-items
      (pred first) (concat selected-items [first])
      :else (recur (concat selected-items [first]) other-elems))))

(defn collatz-sequence
  "Returns the collatz sequence for n.
   The collatz function takes a number n and returns:
   n/2 if n is even
   3n+1 if n is odd
   The sequence is generated by applying the collatz function to the result
    of the previous application.
   The sequence ends when it reaches 1."
  {:level :easy
   :use   '[iterate take-while]
   :alternates '[(implement your own take-until)]}
  [n]
  (take-until #(= % 1) (iterate collatz-fn n)))

(get-todos)