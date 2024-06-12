function toggleMenu() {
            const menu = document.querySelector('.toggle-menu');
            menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
        }

        window.onclick = function(event) {
            if (!event.target.matches('.profile-icon, .profile-icon *')) {
                const menu = document.querySelector('.toggle-menu');
                if (menu.style.display === 'block') {
                    menu.style.display = 'none';
                }
            }
        }