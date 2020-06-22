function aClick() {

    //
    // function getPositionXY(element) {
    //     var rect = element.getBoundingClientRect();
    //     document.getElementById('annoBar').innerHTML =
    //         'X: ' + rect.x + ', ' + 'Y: ' + rect.y
    // }

    var el = document.getElementById("annoBar")
    el.style.position = "absolute"
    //el.style={"{top:50px;left:100px;}"}

    el.style["top"] = "50px"
    el.style["left"] = "100px"

    el.innerText = "SWITCHED AGAIN!!"

}

export function getAnnotationPosition() {
    var el = document.getElementById("annoBar")
    el.style.position = "absolute"
    var d = document.getElementById("topPage")
    var offset = $(d).offset()
    el.style["top"] = offset.top + "px"
    el.innerText = "SHOW ANNOTATION HERE!!"
}