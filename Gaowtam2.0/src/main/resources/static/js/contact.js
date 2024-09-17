console.log("Contact.js");
const basURL = "localhost:8082";
const viewContactModal = document.getElementById("view_contact_modal");

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    console.log("modal is shown");
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "view_contact_modal",
  override: true,
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal() {
  contactModal.show();
}

function closeContactModal() {
  contactModal.hide();
}

//////////////////////////////////////////////////////
//function call to data load
async function loadContactdata(id) {
  console.log(id);

  ////////////////////////////////////////////////////////////////
  /////////api thake data call///http://localhost:8082 project er server

  ///////////////////////// nicher funtion ti Promise retrun kortece,amra promise cy na ty code change kora lagbe/////////////////////////////////////////////////////

  // fetch(`http://localhost:8082/api/contacts/${id}`)
  //   .then((response) => {
  //     const data = response.json();
  //     console.log(data);
  //     return data;
  //   })
  //   .catch((error) => {
  //     console.log(error);
  //   });

  ////////////////////////////////////////  change code (async and await zukto kora hoyece) ///////////////////////////////////////////////////////////////////////////
  // fetch(`http://localhost:8082/api/contacts/${id}`)
  //   .then(async (response) => {
  //     const data = await response.json();
  //     console.log(data);
  //     return data;
  //   })
  //   .catch((error) => {
  //     console.log(error);
  //   });

  /////////////////////////////////////////////////////////// abr change kora hoice,2ti e  same kaz kore  //////////////////////////////////////////////////////////////////////////////////////

  try {
    const data = await (
      await fetch(`http://${basURL}/api/contacts/${id}`)
    ).json();
    console.log(data);
    console.log(data.name);
    console.log(data.phoneNumber);

    document.querySelector("#contact_name").innerHTML = data.name;
    document.querySelector("#contact_email").innerHTML = data.email;
    document.querySelector("#contact_image").src = data.picture;
    document.querySelector("#contact_address").innerHTML = data.address;
    document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
    document.querySelector("#contact_about").innerHTML = data.description;
    const contactFavorite = document.querySelector("#contact_favorite");
    if (data.favortie) {
      contactFavorite.innerHTML =
        '<i class="fas fa-star text-yellow-400"></i><i class="fas fa-star text-yellow-400"></i><i class="fas fa-star text-yellow-400"></i><i class="fas fa-star text-yellow-400"></i><i class="fas fa-star text-yellow-400"></i>';
    } else {
      contactFavorite.innerHTML = "Not Favorite Contact";
    }
    document.querySelector("#contact_website").href = data.websiteLink;
    document.querySelector("#contact_website").innerHTML = data.websiteLink;
    document.querySelector("#contact_linkedin").href = data.linkedInLink;
    document.querySelector("#contact_linkedin").innerHTML = data.linkedInLink;
    openContactModal();
  } catch (error) {
    console.log("Error: ", error);
  }
}
////////////////delete contact

async function deleteContact(id) {
  Swal.fire({
    title: "Do you want to delete the contact?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Delete",
  }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
      const url = `http://${basURL}/user/contacts/delete/` + id;
      window.location.replace(url); /* redirect url */
    }
  });
}
