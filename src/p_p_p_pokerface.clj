(ns p-p-p-pokerface)

(defn suit [card]
  "Exercise 1: Write the function (suit card) which takes a singe card and
   returns the suit of the card as a one character string."
  (let [[_ s] card]
    (str s)))

(assert (= "H" (suit "2H")))
(assert (= "D" (suit "2D")))
(assert (= "C" (suit "2C")))
(assert (= "S" (suit "3S")))

(defn rank [card]
  "Exercise 2: Write the function (rank card) which takes a single card and
   returns the rank as a number between 2 and 14."
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

(def high-seven                   ["2H" "3S" "4C" "5C" "7D"])
(def pair-hand                    ["2H" "2S" "4C" "5C" "7D"])
(def two-pairs-hand               ["2H" "2S" "4C" "4D" "7D"])
(def three-of-a-kind-hand         ["2H" "2S" "2C" "4D" "7D"])
(def four-of-a-kind-hand          ["2H" "2S" "2C" "2D" "7D"])
(def straight-hand                ["2H" "3S" "6C" "5D" "4D"])
(def low-ace-straight-hand        ["2H" "3S" "4C" "5D" "AD"])
(def high-ace-straight-hand       ["TH" "AS" "QC" "KD" "JD"])
(def flush-hand                   ["2H" "4H" "5H" "9H" "7H"])
(def full-house-hand              ["2H" "5D" "2D" "2C" "5S"])
(def straight-flush-hand          ["2H" "3H" "6H" "5H" "4H"])
(def low-ace-straight-flush-hand  ["2D" "3D" "4D" "5D" "AD"])
(def high-ace-straight-flush-hand ["TS" "AS" "QS" "KS" "JS"])

(defn pair? [hand]
  "Exercise 3: Write the function (pair? hand) that returns true if there is a
   pair in hand and false if there is no pair in hand."
  (let [ranks (map rank hand)
        freqs (into #{} (vals (frequencies ranks)))]
    (contains? freqs 2)))

(assert (pair? pair-hand))
(assert (not (pair? high-seven)))

(defn three-of-a-kind? [hand]
  "Exercise 4: Write the function (three-of-a-kind? hand) that returns true if
   the hand contains a three of a kind."
  (let [ranks (map rank hand)
        freqs (into #{} (vals (frequencies ranks)))]
    (contains? freqs 3)))

(assert (not (three-of-a-kind? two-pairs-hand)))
(assert (three-of-a-kind? three-of-a-kind-hand))

(defn four-of-a-kind? [hand]
  "Exercise 5: Write the function (four-of-a-kind? hand) that returns true if
   the hand contains a four of a kind."
  (let [ranks (map rank hand)
        freqs (into #{} (vals (frequencies ranks)))]
    (contains? freqs 4)))

(assert (not (four-of-a-kind? two-pairs-hand)))
(assert (four-of-a-kind? four-of-a-kind-hand))

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
