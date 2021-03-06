The [Gallery-View](Gallery-View) show photos in a scrollable view:

* If you swipe up or down you will see more photos.
* If you tap on a photo it will be opend in the [Image-View](Image-View).
* The Symbol(s) in the actionbar and the menu item(s) 
    * define which photos you want to see (see _current set_ below).

![](https://raw.githubusercontent.com/k3b/AndroFotoFinder/master/wiki/png/Gallery.png)

##Current Set

The [Gallery-View](Gallery-View) show the photos of the device`s _current set_.

The **current set** is either the result of

* SearchFiter + CurrentFolder + Sorter or
* SearchFiter + CurrentGeoArea + Sorter

The **SearchFiter** is used to find a set of photos by search criteria. 

The **CurrentFolder** or **CurrentGeoArea** is used to navigate
in the SearchFiter`s resultset.

The **Sorter** determines the order in which the 
result photos are presented in the [Gallery-View](Gallery-View) and 
in the [Image-View](Image-View).

###Navigation

The [Gallery-View](Gallery-View) has these elements:

* via the **SearchFiter** symbol (or menu item "Filter") you can open the [Filter-View](Filter-View) to define the SearchFiter. 
* via the **CurrentFolder** symbol (or menu item "Select Folder") you can open a [Folder-Picker](Folder-Picker) to pick the CurrentFolder.
* via the **CurrentGeoArea** symbol (or menu item "Select Area") you can open a [Geographic-Map](Geographic-Map) to pick the CurrentGeoArea.
* via the menu item "Sort xxx" you see the current sort order. 
    * Example "Sort: Name ^" means "sort by name ascending". "^" means ascending; "v" means descending.
    * If you click on the "Sort xxx" menuitem you get a submenu with the different sort criteria.
    * If you select the same sort criteria again you toggle between ascending and descending.
        * Example: if current sort is "Sort: Name ^" and you select "Name" again the sort will become "Sort: Name v".

###Example Usecase

* The device contains 15000 photos.
* You [search](Filter-View) is "photos taken in 2007" (that photo date is between _2007-01-01_ and _2008-01-01_").
* If you open the [Current folder picker](Folder-Picker) you will see only those folders that contain images taken in 2007.
    * The resultset contains 79 folders with 1166 photos.
* If you open the [Current geo area picker](Geographic-Map) the map will show markers of photos taken in 2007.
    * The resultset contains 584 photos that contain geo-data.
* If you [pick the current Folder](Folder-Picker) "Job" the gallery will show only photos from folder "Job" or subfolders of "Job" from 2007.
    * Photos from other years are not visible - they are filtered out.

