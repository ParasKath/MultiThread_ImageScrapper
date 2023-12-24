const resultList = document.querySelector('ul.result');
const urlInput = document.querySelector('input[name=url]')
const error = document.getElementById('error');
const dataLoader = document.getElementById('dataloader');
dataLoader.style.display="none";
error.style.display="none"

apiCallBack = function(xhr, callback) {
    if (xhr.readyState == XMLHttpRequest.DONE) {
        if (xhr.status != 200) {
            let message = xhr.status + ":" + xhr.statusText + ":"
                + xhr.responseText;
            alert(message);
            throw 'API call returned bad code: ' + xhr.status;
        }
        let response = xhr.responseText ? JSON.parse(xhr.responseText)
            : null;
        if (callback) {
            callback(response);
        }
    }
}

updateList = function(response) {
    resultList.innerHTML = '';
    urlInput.value = "";
    dataLoader.style.display="none";
    if(response==null)
    {
        error.style.display="block";
        error.innerHTML="Wrong URL";
        return;
    }
    if(response.length==0)
    {
        error.style.display="block"
        error.innerHTML="No Image Found on this page";
        return;
    }
    for (let i = 0; i < response.length; i++) {
        let img = document.createElement("img");
        img.width=200;
        img.src = response[i];
        resultList.appendChild(img);
    }
}

makeApiCall = function(url, method, obj, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.onreadystatechange = apiCallBack.bind(null, xhr, callback);
    xhr.send(obj ? obj instanceof FormData || obj.constructor == String ? obj : JSON.stringify(obj) : null);
}

document.querySelector('button').addEventListener("click", function(event) {
    event.preventDefault();
    dataLoader.style.display="block";
    makeApiCall('/main?url=' + urlInput.value, 'POST', null, updateList);
    resultList.innerHTML = '';
    error.style.display="none";
});