(ns p-p-p-pokerface)

(defn suit [card]
  "Exercise 1: Write the function (suit card) which takes a singe card and
   returns the suit of the card as a one character string.
       (suit \"2H\") => \"H\"
       (suit \"2D\") => \"D\"
       (suit \"2C\") => \"C\"
       (suit \"3S\") => \"S\""
  (let [[_ s] card]
    (str s)))

(assert (= "H" (suit "2H")))
(assert (= "D" (suit "2D")))
(assert (= "C" (suit "2C")))
(assert (= "S" (suit "3S")))

(defn rank [card]
  "Exercise 2: Write the function (rank card) which takes a single card and
   returns the rank as a number between 2 and 14.
       (rank \"2H\") => 2
       (rank \"4S\") => 4
       (rank \"TS\") => 10
       (rank \"JS\") => 11
       (rank \"QS\") => 12
       (rank \"KS\") => 13
       (rank \"AS\") => 14"
  (let [[r _] card
        ranks [\2 \3 \4 \5 \6 \7 \8 \9 \T \J \Q \K \A]]
    (+ 2 (.indexOf ranks r))))

(assert (= 2 (rank "2H")))
(assert (= 4 (rank "4S")))
(assert (= 10 (rank "TS")))
(assert (= 11 (rank "JS")))
(assert (= 12 (rank "QS")))
(assert (= 13 (rank "KS")))
(assert (= 14 (rank "AS")))

(defn pair? [hand]
  nil)

(defn three-of-a-kind? [hand]
  nil)

(defn four-of-a-kind? [hand]
  nil)

(defn flush? [hand]
  nil)

(defn full-house? [hand]
  nil)

(defn two-pairs? [hand]
  nil)

(defn straight? [hand]
  nil)

(defn straight-flush? [hand]
  nil)

(defn value [hand]
  nil)
