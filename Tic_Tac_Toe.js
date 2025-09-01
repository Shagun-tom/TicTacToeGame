let boxes = document.querySelectorAll('.box');
let newbtn = document.querySelector('#new-btn');
let resetbtn = document.querySelector('#reset-btn');
let msgcontainer = document.querySelector('.print-msg');
let msg = document.querySelector('#msg');

let turnO = true;
let count =0;

const winPatterns = [
    [0,1,2],
    [3,4,5],
    [6,7,8],
    [0,3,6],
    [1,4,7],
    [2,5,8],
    [0,4,8],
    [6,4,2]
];

boxes.forEach((box) => {
   box.addEventListener('click',()=>{
    if(turnO){
        box.innerText = 'O';
        turnO = false;
    }else{
        box.innerText = "X";
        turnO = true;
    }
    box.disabled = true;
    count++;
    let isWin = checkWin();
     if(count===9 && !isWin){
            gameDraw();
        }
   });
});

const checkWin = ()=>{
    for(let pattern of winPatterns){
        let pos1 = boxes[pattern[0]].innerText;
        let pos2 = boxes[pattern[1]].innerText;
        let pos3 = boxes[pattern[2]].innerText;
    if(pos1!='' && pos2!='' && pos3!=''){
        if(pos1 === pos2 && pos2 === pos3){
            showWinner(pos1);
            return true;
        }
       
    }
    }
    return false;
};
const gameDraw = () => {
  msg.innerText = `Game was a Draw.`;
  msgcontainer.classList.remove("hide");
  disableBoxes();
};

const showWinner = (Winner)=>{
            msg.innerText = 'Congratulations🎉 winner is '+Winner;
            msgcontainer.classList.remove('hide');
            disableBoxes();
}
const disableBoxes = ()=>{
    for(let box of boxes){
        box.disabled = true;
    }
};
const enableBoxes = ()=>{
    for(let box of boxes){
        box.disabled = false;
        box.innerText = "";
    }

};

const reset = ()=>{
    enableBoxes();
    msgcontainer.classList.add('hide');
    turnO = true;
    count = 0;
};
newbtn.addEventListener('click',reset);
resetbtn.addEventListener('click',reset);
