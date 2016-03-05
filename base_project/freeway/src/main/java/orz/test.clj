(defn func [a] (- a 1))

(loop [a -111] (if (> a 0) (+ a 10) (recur (+ a 1))))


