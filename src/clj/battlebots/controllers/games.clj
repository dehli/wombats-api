(ns battlebots.controllers.games
  (:require [ring.util.response :refer [response]]
            [battlebots.services.mongodb :as db]
            [battlebots.arena :as arena]))

(def games-coll "games")

(defn get-games
  "returns all games or a specified game"
  ([]
   (response (db/find-all games-coll)))
  ([game-id]
   (response (db/find-one games-coll game-id))))

(defn add-game
  "adds a new game"
  []
  (let [arena (arena/new-arena arena/large-arena)
        game {:initial-arena arena
              :rounds []
              :players []}]
    (response (db/insert-one games-coll game))))

(defn remove-game
  "removes a game" 
  [game-id]
  (db/remove-one games-coll game-id)
  (response "ok"))

(defn get-rounds
  "returns all rounds, or a specifed round, for a given game"
  ([game-id]
    (response []))
  ([game-id round-id]
    (response {})))

(defn add-round
  "adds a new round to a given game"
  [game-id]
    (response {}))

(defn get-players
  "returns all players, or a specified player, for a given game"
  ([game-id]
    (response []))
  ([game-id player-id]
    (response {})))

(defn add-player
  "add a new player to a given game"
  [game-id]
  (response {}))