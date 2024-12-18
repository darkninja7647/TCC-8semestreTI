
const capacidade = document.getElementById('capacidade');
const tipoQuarto = document.getElementById('tipoQuarto');

// Atualiza o tipo de quarto com base na capacidade selecionada
capacidade.addEventListener('change', () => {
    switch (parseInt(capacidade.value)) {
        case 1:
            tipoQuarto.value = "Solteiro";
            break;
        case 2:
            tipoQuarto.value = "Casado";
            break;
        case 3:
        case 4:
            tipoQuarto.value = "Suite";
            break;
        case 5:
        case 6:
            tipoQuarto.value = "Familia";
            break;
    }
});

// Atualiza a capacidade com base no tipo de quarto selecionado
tipoQuarto.addEventListener('change', () => {
    switch (tipoQuarto.value) {
        case "Solteiro":
            capacidade.value = "1";
            break;
        case "Casado":
            capacidade.value = "2";
            break;
        case "Suite":
            capacidade.value = "3"; // Considera como padrão o valor mais baixo
            break;
        case "Familia":
            capacidade.value = "5"; // Considera como padrão o valor mais baixo
            break;
    }
});
