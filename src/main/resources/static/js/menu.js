function menuClick(clicked){

    $(".left-menu")
        .children("table")
        .children("tbody")
        .children("tr")
        .children("td.active").removeClass("active");
    $(clicked).addClass("active");
    clearMainContent($(clicked).text().toLowerCase());

}

function clearMainContent(show){
    $(".block-content").hide();
    var id = "#"+show;
    $(id).show();
}