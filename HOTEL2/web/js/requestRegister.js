document.addEventListener("DOMContentLoaded", function () {
    // Função para alternar o dropdown
    function toggleDropdown(element) {
        const dropdownContainer = element.closest('.input_field');
        const dropdownList = dropdownContainer.querySelector('.dropdownlist');
        dropdownList.style.display = dropdownList.style.display === 'block' ? 'none' : 'block';
    }

    // Clique no ícone para abrir/fechar o dropdown
    document.querySelectorAll('.dropdownicon').forEach(icon => {
        icon.addEventListener('click', function (event) {
            event.stopPropagation();
            toggleDropdown(icon);
        });
    });

    // Fechar dropdown ao clicar fora
    document.addEventListener('click', function () {
        document.querySelectorAll('.dropdownlist').forEach(list => {
            list.style.display = 'none';
        });
    });

    // Atualizar o input ao selecionar um item
    document.querySelectorAll('.dropdownlist').forEach(list => {
        list.addEventListener('click', function (event) {
            if (event.target.tagName === 'LI') {
                const inputField = list.closest('.input_field').querySelector('input');
                const selectedText = event.target.textContent.trim(); // Texto visível
                const selectedValue = event.target.dataset.value; // ID do item

                inputField.value = selectedText; // Exibir o texto no campo
                inputField.setAttribute('data-selected-value', selectedValue); // Armazenar o ID como atributo

                list.style.display = 'none';
            }
        });
    });

    // Adicionar o ID no formulário ao enviar
    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', function () {
            const dropdownInputs = form.querySelectorAll('.input_field input[data-selected-value]');
            dropdownInputs.forEach(input => {
                const hiddenInput = document.createElement('input');
                hiddenInput.type = 'hidden';
                hiddenInput.name = input.name;
                hiddenInput.value = input.getAttribute('data-selected-value'); // Valor do ID
                form.appendChild(hiddenInput);
                input.name = ''; // Evitar conflito de envio
            });
        });
    });
});