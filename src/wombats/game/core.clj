(ns wombats.game.core
  (:require [wombats.game.initializers :as i]
            [wombats.game.finalizers :as f]))

(defn- game-over?
  "End game condition"
  [game-state]
  ;; TODO For now we're only calculating a fix number of rounds
  ;; This will have to be updated with the base condition for
  ;; each game type
  (= 10 (get-in game-state [:frame :frame/frame-number])))

(defn- game-loop
  "Game loop"
  [game-state]
  (loop [current-game-state game-state]
    (if (game-over? current-game-state)
      current-game-state
      (-> current-game-state
          (i/initialize-frame)
          (recur)))))

(defn initialize-game
  "Main entry point for the game engine"
  [game-state]
  (-> game-state
      (i/initialize-game)
      (game-loop)
      (f/finalize-game)
      (clojure.pprint/pprint)))
