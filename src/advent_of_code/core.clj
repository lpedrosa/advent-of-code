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

(def part1-in "day1/part1-in.txt")

(defn part1-answer []
  (let [in (-> part1-in
               io/resource
               slurp)]
    (which-floor in)))
