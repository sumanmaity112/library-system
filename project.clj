(defproject library.system "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license
  {:name "Eclipse Public License"
   :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-jetty-adapter "1.7.0-RC1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.6.1"]
                 [clj-time "0.14.2"]
                 [org.clojure/java.jdbc "0.7.6"]
                 [org.postgresql/postgresql "42.2.2"]
                 [environ "1.1.0"]
                 [ring/ring-mock "0.3.2"]
                 [ragtime "0.7.2"]]
  :plugins
  [[lein-environ "1.1.0"]]
  :main library.system.application)
