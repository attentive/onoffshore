(ns blog.index
  (:require [hiccup.page :refer [html5 include-css]]
            [blog.layout :refer [with-navbar]]))

(defn render [{global-meta :meta posts :entries :as opts}]
  (with-navbar opts
    [:ul
     [:li [:a {:href "/about.html"} "About Page"]]
     [:li [:a {:href "/feed.rss"} "RSS"]]
     [:li [:a {:href "/atom.xml"} "Atom Feed"]]]
    [:ul.items.columns.small-12
     (for [post posts]
       [:li
        [:a {:href (:permalink post)}(:title post)]])]))
