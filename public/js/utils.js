document.addEventListener("DOMContentLoaded", () => {
    document.body.insertAdjacentHTML('afterbegin', `
        <nav class="navbar navbar-expand-lg bg-body-tertiary mb-3 sticky-top">
            <div class="container">
                <a class="navbar-brand" href="index.html">
                    <i class="fa-regular fa-circle-play"></i> FilmBox
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="index.html">
                                <i class="fa-solid fa-house"></i> Početna
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="list.html">
                                <i class="fa-solid fa-table-list"></i> Lista
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cinema.html">
                                <i class="fa-solid fa-clapperboard"></i> Bioskopi
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="time-table.html">
                                <i class="fa-solid fa-clock-rotate-left"></i> Projekcije
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ticket.html">
                                <i class="fa-solid fa-tag"></i> Moje Karte
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    `)
})

const bootstrapClasses = {
    popup: 'card',
    cancelButton: 'btn btn-danger',
    denyButton: 'btn btn-secondary',
    confirmButton: 'btn btn-primary'
}

function showLoading() {
    Swal.fire({
      title: 'Podaci se učitavaju',
      text: 'Molimo sačekajte dok dopremimo najsvežije podatke.',
      allowOutsideClick: false,
      customClass: bootstrapClasses,
      didOpen: () =>{
      Swal.showLoading()
      }
    })
}

function showConfirm(msg, callback) {
    Swal.fire({
        title: msg,
        showCancelButton: true,
        confirmButtonText: 'Da, želim',
        cancelButtonText: 'Ne, odustani',
        icon: "question",
        customClass: bootstrapClasses
    }).then(result => {
        if (result.isConfirmed) {
            callback()
            Swal.fire({
                title: "Uspešno izvršeno",
                confirmButtonText: 'Uredu',
                icon: "success",
                customClass: bootstrapClasses
            })
        }
    })
}

async function retrieveData(url, callback) {
    showLoading()
    const rsp = await fetch(url)
    const data = await rsp.json()
    callback(data)
    Swal.close()
}

function formatDate(iso) { 
    return new Date(iso).toLocaleString('sr-RS',{
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
    }
    )
}