// console.log("Script Loded");

// let currentTheme=getTheme();

// // when document loaded
//   document.addEventListener('DOMContentLoaded',()=>{
//     changeTheme();
//   });

// //TODO
// function changeTheme()
// {
//     // set to web page
//     changePaheTheme(currentTheme,currentTheme);
//     console.log(currentTheme);  
//    //set the listener to change theme button
//    const changeThemeButton=document.querySelector('#theme_chamge_button');
//    changeThemeButton.addEventListener('click',(event)=>{
//         const oldTheme=currentTheme;
//         if(currentTheme=="dark")
//             {
//             currentTheme="light"
//             }
//             else
//             {
//             currentTheme="dark";
//             }
//             changePaheTheme(currentTheme,oldTheme);
//             console.log(currentTheme+"  "+oldTheme);  
//    });
// }


// //get theme from localstorage
// function getTheme()
// {
//     let theme=localStorage.getItem("theme");
//     return theme?theme:"light";
// }
// //set theme to localstorage
// function setTheme(theme)
// {
//     localStorage.setItem("theme",theme);
// }

// // Change current page theme
// function changePaheTheme(theme,oldTheme)
// {
//      //locasotorage er moddhe update theme
//      setTheme(theme);
//      //remove the current theme
//      document.querySelector('html').classList.remove(oldTheme);
//      //set current theme
//      document.querySelector('html').classList.add(theme);

//      //change the text of button
//      const changeThemeButton=document.querySelector('#theme_chamge_button');
//      changeThemeButton.querySelector('span').textContent=
//      theme=="light"?" Dark ":" Light ";
// }
console.log(localStorage.getItem("theme"));
let currentTheme=getTheme();
const changeThemeButton=document.querySelector('#theme_chamge_button');
changeThemeButton.querySelector('span').textContent=currentTheme=="light"?" Dark ":" Light ";
document.querySelector('html').classList.add(currentTheme);
document.addEventListener('DOMContentLoaded',()=>{
  changeTheme();
});

function getTheme()
{
  let theme=localStorage.getItem("theme");
  return theme?theme:"light";
}
function changeTheme()
{
  
  changeThemeButton.addEventListener('click',(event)=>
  {
    let oldTheme=currentTheme;
    if(currentTheme=="dark")
    {
      currentTheme="light";
    }
    else
    {
      currentTheme="dark";
    }
    document.querySelector('html').classList.remove(oldTheme);
    document.querySelector('html').classList.add(currentTheme);
    localStorage.setItem("theme",currentTheme);
    changeThemeButton.querySelector('span').textContent=currentTheme=="light"?" Dark ":" Light ";
  });
  
}