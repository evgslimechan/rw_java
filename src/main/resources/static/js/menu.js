function menuClick(clicked){

    clearMainContent($(clicked).text().toLowerCase().trim());

}

function clearMainContent(show){
    window.location.href = show+"";
}