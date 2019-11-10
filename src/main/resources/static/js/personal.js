function closePanel(){
    if(token.length == 0){
        window.location.href = "login";
    }
    $("#add-panel").find("tr[name='idinfo']").hide();
    $("#add-panel").hide();
    $("#add-panel").find("input[type=text]").attr("value","");
    $("#add-panel").find("input[type=tel]").attr("value","");
    $("#add-panel").find("input[type=email]").attr("value","");
    $("#shadow").hide();
}
function openPanel(){
    if(token.length===0){
        window.location.href = "login";
    }
    $("#add-panel").show();
    $("#shadow").show();
}

function edit(but){
    if(token.length == 0){
        window.location.href = "login";
    }
    var info = but.parentElement.parentElement;

    var id = $(info).find("a[name='id']").get(0).innerHTML;
    var fio = $(info).find("h3.fio").get(0).innerHTML;

    $("#add-panel").find("tr[name='idinfo']").show();

    $("#add-panel").find("td[name='id']").get(0).innerHTML = id;
    $("#add-panel").find("input[name='fio']").attr("value", fio);

    openPanel();
}
function add(){
    $("#add-panel").find("tr[name='idinfo']").show();
    $("#add-panel").find("td[name='id']").get(0).innerHTML = "<input type='text' name='id'/>";
    $("#add-panel").find("input[type=button]").attr("onclick", "tryAdd(this)");
    openPanel();
}
function tryAdd(button) {

    var id = document.getElementsByName("idinfo")[0].childNodes[1].firstChild.value;
    var pass = document.getElementsByName("password")[0];
    var fio = document.getElementsByName("fio")[0];
    var phone = document.getElementsByName("phone")[0];
    var role = document.getElementsByName("role")[0];
    role = role.options[role.selectedIndex].text;
    var $formData = new FormData();
    $formData.append("id", id);
    $formData.append("password", pass.value);
    $formData.append("fio", fio.value);
    $formData.append("phone", phone.value);
    $formData.append("role", role);

    $.ajax({
        url: "http://"+getURL()+"/api/personal/add?token="+token,
        type: "POST",
        data: $formData,
        error: (data)=>{
            alert("ERROR "+data.toString());
        },
        success: (data)=>{
            window.location.href = "personal";
        },
        complete: ()=>{},
        processData: false,
        contentType: false
    });
}
function tryRemove(button){
    if(token.length === 0){
        window.location.href = "login";
    }
    var id = $(button.parentElement.parentElement).find("a[name='id']").get(0).innerHTML;
    $.ajax({
        url: "http://"+getURL()+"/api/personal/remove?id="+id+"&token="+token,
        type: "GET",
        data: "",
        error: (data)=>{
            alert("ERROR "+data.toString());
        },
        success: (data)=>{
            closePanel();
            window.location.href = "personal";
        },
        complete: ()=>{},
        processData: false,
        contentType: false
    });
}
function tryEditPerson(button){
    if(token.length == 0){
        window.location.href = "login";
    }

    var id = document.getElementsByName("idinfo")[0].childNodes[1].innerHTML;
    var pass = document.getElementsByName("password")[0];
    var fio = document.getElementsByName("fio")[0];
    var phone = document.getElementsByName("phone")[0];
    var role = document.getElementsByName("role")[0];
    role = role.options[role.selectedIndex].text;
    var $formData = new FormData();
    $formData.append("id", id);
    $formData.append("password", pass.value);
    $formData.append("fio", fio.value);
    $formData.append("phone", phone.value);
    $formData.append("role", role);

    $.ajax({
        url: "http://"+getURL()+"/api/personal/edit?token="+token,
        type: "POST",
        data: $formData,
        error: (data)=>{
            alert("ERROR "+data.toString());
        },
        success: (data)=>{
            window.location.href = "personal";
        },
        complete: ()=>{},
        processData: false,
        contentType: false
    });
}
function getURL(){
    return window.location.href.split("/")[2];
}