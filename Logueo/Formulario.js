var btnNoSubmitPost = document.getElementById("btnNoSubmitPost");
btnNoSubmitPost.addEventListener('click', function () {
    let email = document.getElementById('exampleInputEmail1').value;
    let pass = document.getElementById('exampleInputPassoword1').value;
    var params = new URLSearchParams();
    params.append('nombre', email);
    params.append('password', pass);

    axios.post('http://localhost:4567/saludar', params)
    .then(function (response) {
        console.log(response);
        console.log("verdad: " + response.data);
        alert(response.data)
    })
    .catch(function (error) {
        console.log("error: " + error);
    })
});