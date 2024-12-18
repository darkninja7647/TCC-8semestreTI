function selecionarQuarto(image, idRoom) {
    // Habilita os botões "Alterar" e "Deletar"
    const alterarBtn = document.getElementById("alterar-btn");
    const deletarBtn = document.getElementById("deletar-btn");
    alterarBtn.disabled = false;
    deletarBtn.disabled = false;

    // Armazena o ID do quarto no atributo value dos botões "Alterar" e "Deletar"
    alterarBtn.value = idRoom;
    deletarBtn.value = idRoom;

    // Remove a classe 'selecionado' de todas as imagens e restaura a opacidade para 80%
    document.querySelectorAll('.room-grid img').forEach(img => {
        img.classList.remove('selecionado');
        img.style.opacity = 0.4; // Define opacidade padrão
    });

    // Adiciona a classe 'selecionado' à imagem clicada e define opacidade para 100%
    image.classList.add('selecionado');
    image.style.opacity = 1; // Opacidade total para imagem selecionada
}

function alterarQuarto() {
    const alterarBtn = document.getElementById("alterar-btn");
    const idRoom = alterarBtn.value;

    if (idRoom) {
        // Lógica para alterar o quarto com o ID específico
        console.log("Alterando quarto com ID:", idRoom);
        // Adicione aqui a lógica de alteração...
    }
    window.location.href = 'http://localhost:8080/HOTEL2/Controller_Room?btnoperacaoroom=Update&txtIdRoom=' + idRoom;
}

function deletarQuarto(event) {
    event.preventDefault(); // Previne o comportamento padrão do botão

    const deletarBtn = document.getElementById("deletar-btn");
    const idRoom = deletarBtn.value;

    if (idRoom) {
        const confirmacao = confirm("Deseja deletar ?");

        if (confirmacao) {
            // Redireciona para a URL de deleção
            window.location.href = 'http://localhost:8080/HOTEL2/Controller_Room?btnoperacaoroom=Delete&txtIdRoom=' + idRoom;
        } else {
            console.log("Ação de deleção cancelada pelo usuário.");
        }
    }
}


// Função para aplicar o filtro baseado nos checkboxes selecionados
function aplicarFiltro() {
    // Captura o estado dos checkboxes
    const ocupado = document.getElementById('ocupado-checkbox').checked;
    const livre = document.getElementById('livre-checkbox').checked;
    const governanca = document.getElementById('governanca-checkbox').checked;
    const manutencao = document.getElementById('manutencao-checkbox').checked;
    const solteiro = document.getElementById('single-checkbox').checked;
    const casado = document.getElementById('double-checkbox').checked;
    const suite = document.getElementById('suite-checkbox').checked;
    const familia = document.getElementById('family-checkbox').checked;
    // Filtra os quartos com base no status
    document.querySelectorAll('.room-button').forEach(button => {
        const status = button.getAttribute('data-status1');
        const tipo = button.getAttribute('data-status2');
        button.style.display = 'none'; // Esconde todos inicialmente

        if (
                (ocupado && status === 'Ocupado') ||
                (livre && status === 'Livre') ||
                (governanca && status === 'Governança') ||
                (manutencao && status === 'Manutenção') ||
                (solteiro && tipo === 'Solteiro') ||
                (casado && tipo === 'Casado') ||
                (suite && tipo === 'Suite') ||
                (familia && tipo === 'Familia') ||
                (!ocupado && !livre && !governanca && !manutencao && !solteiro && !casado && !suite && !familia) // Mostra todos se nenhum filtro está ativo
                ) {
            button.style.display = 'inline-block';
        }
    });
}
