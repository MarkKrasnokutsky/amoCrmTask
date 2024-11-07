document.addEventListener("DOMContentLoaded", () => {
let startTime = new Date().valueOf();
    document.getElementById('application-form').addEventListener('submit', function(event) {
        event.preventDefault(); // предотвращаем стандартное поведение формы

        // Извлекаем данные из формы и сохраняем в переменные
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        const price = document.getElementById('price').value;

        // Создаем объект с данными для отправки
        const dataContact = {
            name: name,
            phone: phone,
            email: email
        };

        // Отправляем данные на сервер
        fetch('http://localhost:8080/api/v1/contact/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataContact),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('ERROR');
                }
                return response.json();
            })
            .then(data => {
                console.log('Успех:', data);
                const id = data.body.id;
                loadedSeconds = (new Date().valueOf() - startTime) / 1000;
                const dataLead = {
                    pipeline_id: 8851754,
                    contact_id: id,
                    leadName: "Сделка с " + name,
                    price: price,
                    flag: loadedSeconds > 30
                }
                fetch('http://localhost:8080/api/v1/lead/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(dataLead),
                })
                     .then(response => response.json())
                     .then(anotherData => {
                         console.log('Сделка создана:', dataLead);
                     });

                alert('Заявка успешно отправлена!');
            })
            .catch((error) => {
                console.error('Ошибка:', error);
                alert('Произошла ошибка при отправке заявки на создание контакта.');
            });
    });
});
