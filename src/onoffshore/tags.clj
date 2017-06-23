(ns onoffshore.tags
  (:require [hiccup.page :refer [html5 include-css]]
            [onoffshore.layout :refer [with-navbar]]))

(defn render [{global-meta :meta posts :entries entry :entry :as opts}]
  (with-navbar opts   
    [:h1 (:title entry)]
    [:ul.items.columns.small-12
     (for [post posts]
       [:li (:title post)])]))
