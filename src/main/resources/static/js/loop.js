/* 1) Создать либо копировать 5 div блоков и задать в innerHTML их id 
 * 2) Перевернуть div блоки (поставить с 5 по первый)
   3) Задать каждому блоку background с красным оттенком его id*5
   4) Выведите сетку из блоков 3 х 3
   5) Сгенерировать сетку календаря (1-30)
   6) Менять цвета блоку пока мышь находиться в нем (while(div.querySelector(":hover")))
   7) Перебрать строку через итератор заменить все цифры на .
   8) Удалить все созданные ранее блоки через forEach()
 */

/**
 * 
 * @param {Array} list 
*/
function changeColor(list){

  /**
   * @param {HTMLElement} div
   */
    list.forEach(div=>{
        div.style.color = "red";
    });

}