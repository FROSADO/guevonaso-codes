var fsa;
function init() {
    fsa = Joint.dia.fsa;
    Joint.paper("world", 800, 400);

}

var estados = new Array();
var uniones = new Array();

function addState() {
    var name = document.getElementById("name");
    if (estados[name.value] != undefined) {
        state = estados[name.value];
        alert("Nombre de estado ya existe : " + state);
    } else {
        state = fsa.State.create({
            position : {
                x : 250,
                y : 100
            },
            label : name.value
        });
        estados[name.value] = state;
    }

}

function joinStates () {
    var origen = document.getElementById("origen");
    var destino = document.getElementById("destino");
    var label = document.getElementById("etiqueta");
    var union = origen.value + ">>" + destino.value;
    if (uniones[union] != undefined) { 
        alert ("Union ya existe" + union);
    } else {
        estadoOrigen = estados[origen.value];
        if (estadoOrigen == undefined) {
            alert ("Estado origen no existe " + origen.value);
            return;
        }
        estadoDestino = estados[destino.value];
        if (estadoDestino == undefined) {
            alert ("Estado destino no existe" + destino.value);
            return;
        }
        etiqueta = "";
        if (label.value != undefined) {
            etiqueta = label.value;
        }
        uniones[union] = etiqueta;
        estadoOrigen.joint(estadoDestino,
            (fsa.arrow.label = etiqueta,
             fsa.arrow)
            ).hideHandle().register(estados);
    }
}