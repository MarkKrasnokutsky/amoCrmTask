const url = 'http://localhost:8080/api/v1';
const pipeline_id = 8851754;
document.addEventListener("DOMContentLoaded", () => {
    let startTime = new Date().valueOf();
    document.getElementById('application-form').addEventListener('submit', function(e) {
        e.preventDefault();

        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        const price = document.getElementById('price').value;

        const nameRegex = /^[a-zA-Zа-яА-ЯёЁ]{4,40}$/;
        const phoneRegex = /^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/;
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9_-]+$/;

        if (!name || !email || !phone || !price) {
            alert("Все поля должны быть заполнены.");
            return;
        }

        if (!nameRegex.test(name)) {
            alert("Имя должно содержать от 4 до 40 букв (латиница или кириллица).");
            return;
        }

        if (!phoneRegex.test(phone)) {
            alert("Введите корректный номер телефона.");
            return;
        }

        if (!emailRegex.test(email)) {
            alert("Введите корректный адрес электронной почты.");
            return;
        }

        if (price < 0 || Number.isInteger(price)) {
            alert("Цена должна быть положительным целым числом.");
            return;
        }

        const dataContact = {
            name: name,
            phone: phone,
            email: email
        };

        fetch(url + '/contact/add', {
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
                const dataLead = {
                    pipeline_id: pipeline_id,
                    contact_id: data.body.id,
                    leadName: "Сделка с " + name,
                    price: price,
                    flag: ((new Date().valueOf() - startTime) / 1000) > 30
                }
                fetch(url + '/lead/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(dataLead),
                })
                     .then(response => response.json())
                     .then(data => {
                         console.log('Сделка создана:', data);
                     });

                alert('Заявка успешно отправлена!');
            })
            .catch((error) => {
                console.error('Ошибка:', error);
                alert('Произошла ошибка при отправке заявки на создание контакта.');
            });
    });
});
