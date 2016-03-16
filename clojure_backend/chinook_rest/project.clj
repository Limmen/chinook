(defproject chinook_rest "0.1.0-SNAPSHOT"
  :description "REST-API for the chinook database"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [http-kit "2.1.16"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-json-params "0.1.3"]
                 [ring/ring-json "0.4.0"]
                 [clj-json "0.5.3"]
                 [yesql "0.5.2"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]]
  :main ^:skip-aot chinook_rest.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
