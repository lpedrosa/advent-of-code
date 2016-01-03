(ns advent-of-code.core
  (:require [clojure.java.io :as io]))

;; day 1
;; part 1

(defn- next-floor [ch]
  (case ch
    \( 1
    \) -1
    ;; default
    0))

;; this uses recursion
(defn- count-floors [xs floor]
  (if (empty? xs)
    floor
    (count-floors (rest xs) (+ floor (-> xs
                                         first
                                         next-floor)))))

;; this uses a reduce chain
(defn- count-floors-2 [input]
  (->> input
       (map next-floor)
       (reduce + 0)))

(defn which-floor [input]
  (count-floors-2 input))

(def ^:private part1-in "day1/part1-in.txt")

(defn- slurp-resource 
  "slurps a resource.
  CAUTION: not nice for big files"
  [path]
  (slurp (io/resource path)))

(defn part1-answer []
  (-> part1-in
      slurp-resource
      which-floor))

;; part 2

(defn enters-basement 
  ([xs] (enters-basement xs 0 0))
  ([xs floor pos]
   (cond 
     (neg? floor) pos
     (empty? xs) nil
     :else (let [new-xs (rest xs)
                 floor-inc (next-floor (first xs))]
             (enters-basement new-xs (+ floor-inc floor) (inc pos))))))

(defn part2-answer []
  (-> part1-in
      slurp-resource
      enters-basement))
