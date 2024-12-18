/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


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
                inputField.value = event.target.dataset.value;
                list.style.display = 'none';
            }
        });
    });
});