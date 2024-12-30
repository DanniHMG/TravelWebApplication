document.addEventListener('DOMContentLoaded', function() {
    // Smooth scroll para los enlaces de navegación
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    // Animación del contador en el hero
    const counter = document.querySelector('.hero-counter');
    if (counter) {
        let currentActive = 1;
        setInterval(() => {
            const spans = counter.querySelectorAll('span');
            spans.forEach(span => span.style.opacity = '0.3');
            spans[currentActive - 1].style.opacity = '1';
            currentActive = currentActive === 5 ? 1 : currentActive + 1;
        }, 3000);
    }

    // Funcionalidad del modal de video
    const modal = document.getElementById('videoModal');
    const btn = document.querySelector('.watch-video');
    const span = document.getElementsByClassName('close')[0];
    const videoContainer = document.querySelector('.video-container');
    const videoUrl = "https://www.youtube.com/embed/your-video-id"; // Cambia esto por tu ID de video

    btn.onclick = function() {
        modal.style.display = "block";
        videoContainer.querySelector('iframe').src = videoUrl;
    }

    span.onclick = function() {
        modal.style.display = "none";
        videoContainer.querySelector('iframe').src = "";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
            videoContainer.querySelector('iframe').src = "";
        }
    }

    // Animación de aparición al hacer scroll
    const observerOptions = {
        threshold: 0.1
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
            }
        });
    }, observerOptions);

    document.querySelectorAll('.tour-card, .info-card, .discover').forEach(el => {
        el.classList.add('animate-on-scroll');
        observer.observe(el);
    });
});

// Navbar transparente a sólido al hacer scroll
window.addEventListener('scroll', function() {
    const header = document.querySelector('header');
    if (window.scrollY > 50) {
        header.classList.add('solid');
    } else {
        header.classList.remove('solid');
    }
});