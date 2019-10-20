 /**
  *My animation class
  *
  * @class AnimationThread
  */
 class AnimationThread {
   constructor(triggerObj){
        this.timer = null;
        this.up = true;
        /** @type {HTMLDivElement} */
        this.self = triggerObj;
   }

   hover(){
       if(this.self.style.height===""){
           this.self.style.height = "20%";
       }
       if(!this.up){
           clearInterval(this.timer); 
           this.up=!this.up;
       }
       if(this.up)
           this.timer = setInterval(()=>{
                   var h = parseInt(this.self.style.height.split("%")[0])+1;
                   if(h>100){
                       clearInterval(this.timer); 
                       this.up = !this.up;
                       this.endAnimation();
                       return;
                   }
                   this.self.style.height = h+"%";
               },  1);
   }
   hoverOut(){
       if(this.up){
           clearInterval(this.timer); 
           this.up=!this.up;
       }
       if(!this.up){
            this.self.style.borderTop = "1px dashed black";
            this.self.children[0].hidden = true;
           this.timer = setInterval(()=>{
                   var h = parseInt(this.self.style.height.split("%")[0])-1;
                   if(h<=20){
                       clearInterval(this.timer); 
                       this.up = !this.up;
                       return;
                   }
                   this.self.style.height = h+"%";
               }, 1);
        }
   }

   endAnimation(){
       this.self.style.borderTop = "none";
       this.self.children[0].hidden = false;
   }
}

   var elems = new Map();

   Array.from(document.getElementsByClassName("news-info")).forEach(info=>{
       var anim = new AnimationThread(info);
       elems.set(info, anim);
            info.addEventListener("mouseover", function(ev){
                if(elems.get(ev.target)!=null)
                    elems.get(ev.target).hover();
            });
            info.addEventListener("mouseout", function(ev){
                if(elems.get(ev.target)!=null)
                    elems.get(ev.target).hoverOut();
            });
   });
   