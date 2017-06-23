(ns onoffshore.index
  (:require [hiccup.page :refer [html5 include-css]]
            [onoffshore.layout :refer [with-navbar card]]))

(defn render [{global-meta :meta posts :entries :as opts}]
  (with-navbar opts
    #_[:div.row
     (card opts [:a {:href "/about.html"} "About Page"])
     (card opts [:a {:href "/feed.rss"} "RSS"])
     (card opts [:a {:href "/atom.xml"} "Atom Feed"])]
    [:div.row
     (for [post posts]
       (card opts 
        [:a {:href (:permalink post)}(:title post)]))]))
