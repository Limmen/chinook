(ns chinook_rest.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route] ;;compojure is the routing framework
            [org.httpkit.server :refer [run-server]] ;;httpkit is the webserver
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json-params] ;;middleware to return json in http responses
            [ring.middleware.json :as middleware]
            [clj-json.core :as json] ;;clj-json is used to convert between clojure datastructures and json
            [yesql.core :refer [defquery, defqueries]])) ;;yesql is used to access database.

;;Define a database connection spec. (This is standard clojure.java.jdbc.)
(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/chinook"
              :user "guest"
              :password "guest"})

;; Import the SQL query as a function.
;;(defquery get-all-artists "get_all_artists.sql"
;;{:connection db-spec})

;; import multiple queries, names are definied in the sql file with --name
(defqueries "artists.sql"
  {:connection db-spec})
(defqueries "albums.sql"
  {:connection db-spec})
(defqueries "employees.sql"
  {:connection db-spec})
(defqueries "customer.sql"
  {:connection db-spec})
(defqueries "genre.sql"
  {:connection db-spec})
(defqueries "invoiceline.sql"
  {:connection db-spec})
(defqueries "invoice.sql"
  {:connection db-spec})
(defqueries "mediatype.sql"
  {:connection db-spec})
(defqueries "playlist.sql"
  {:connection db-spec})
(defqueries "playlisttracks.sql"
  {:connection db-spec})
(defqueries "track.sql"
  {:connection db-spec})

;;generate-string converts from clojure map to json
(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})


;;compojure route definitions are just functions which accepts request maps and retun response maps.

;;the [] is used to retrieve information form the request map. [id] binds the symbol id to the id parameter in the request map.
(defn handle-get []
  (defroutes api-routes
    (GET "/" [] {:status 200 :headers {"Content-Type" "application/json"} :body {:foo "bar"}})
    (GET "/fun" [] (fn [req] "Do something with req"))
    (GET "/json" []
         (json-response {"hello" "world"}))
    ;;route patterns may include named parameters
    (GET "/artists" [] (get-all-artists))
    (GET "/albums" [] (get-all-albums))
    (GET "/employees" [] (get-all-employees))
    (GET "/customers" [] (get-all-customers))
    (GET "/genres" [] (get-all-genres))
    (GET "/invoicelines" [] (get-all-invoicelines))
    (GET "/invoices" [] (get-all-invoices))
    (GET "/mediatypes" [] (get-all-mediatypes))
    (GET "/playlists" [] (get-all-playlists))
    (GET "/playlisttracks" [] (get-all-playlisttracks))
    (GET "/tracks" [] (get-all-tracks))
    (GET "/hello/:name" [name] (str "Hello " name))))

(defroutes myapp
  (handle-get)
  (POST "/" [] "Create something")
  (PUT "/" [] "Replace something")
  (PATCH "/" [] "Modify Something")
  (DELETE "/" [] "Annihilate something")
  (OPTIONS "/" [] "Appease something")
  (HEAD "/" [] "Preview something")
  (route/not-found "Not Found"))


;; fetching from the database with yesql will return clojure-map datastructure.
;;(println "Artists: " (get-all-artists))

;;run-server is a command provided by httpkit (webserver)

;;the -> operator is used to chain evaluation results.
;; (-> (Math/sqrt 25) int list) can be read:
;; 1. Take the result of (Math/sqrt 25) 2. feed it into the function int 4. Feed that to the function list

;;The wrap-json-body middleware will parse the body of any request with a JSON content-type into a Clojure data structure, and assign it to the :body key.

;;The wrap-json-response middleware will convert any response with a collection as a body (e.g. map, vector, set, seq, etc) into JSON.
(defn -main []
  (println "Starting server on port 5000...")
  (def app
    (-> myapp
        (middleware/wrap-json-body) 
        (middleware/wrap-json-response)
        (wrap-defaults api-defaults)))
  (run-server (wrap-defaults app site-defaults) {:port 5000})
  (println "Server running"))
