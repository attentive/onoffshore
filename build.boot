(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[perun "0.4.2-SNAPSHOT"]
                  [hiccup "1.0.5"]
                  [pandeiro/boot-http "0.6.3-SNAPSHOT"]
		 
		  ; Sass
		  [deraen/boot-sass "0.3.1" :scope "test"]
		  [org.webjars.bower/bootstrap "4.0.0-alpha.6" :exclusions [org.webjars.bower/jquery]]])

(require '[clojure.string :as str]
         '[io.perun :refer :all]
         '[onoffshore.index :as index-view]
         '[onoffshore.post :as post-view]
         '[pandeiro.boot-http :refer [serve]]
	 '[deraen.boot-sass :refer [sass]])

(task-options! 
  sass {:source-map true})

(deftask build
  "Build static detention infrastructure site."
  []
  (comp
        (global-metadata)
        (markdown)
        (draft)
        (slug)
        (ttr)
        (word-count)
        (build-date)
        (gravatar :source-key :author-email :target-key :author-gravatar)
        (render :renderer 'onoffshore.post/render)
        (collection :renderer 'onoffshore.index/render :page "index.html")
        (tags :renderer 'onoffshore.tags/render)
        (paginate :renderer 'onoffshore.paginate/render)
        (assortment :renderer 'onoffshore.assortment/render
                    :grouper (fn [entries]
                               (->> entries
                                    (mapcat (fn [entry]
                                              (if-let [kws (:keywords entry)]
                                                (map #(-> [% entry]) (str/split kws #"\s*,\s*"))
                                                [])))
                                    (reduce (fn [result [kw entry]]
                                              (let [path (str kw ".html")]
                                                (-> result
                                                    (update-in [path :entries] conj entry)
                                                    (assoc-in [path :entry :keyword] kw))))
                                            {}))))
        (static :renderer 'onoffshore.about/render :page "about.html")
        (inject-scripts :scripts #{"start.js"})
        (sitemap)
        (sass)
        (rss :description "Global detention infrastructure")
        (atom-feed :filterer :original)
        (target)
        (notify)))

(deftask dev
  []
  (comp (watch)
        (build)
        (serve :resource-root "public" :port 3001)))
