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