package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Action.BuyItemAction;
import com.zhen.MySillyDesktopCatGame.Controller.Command.Command;
import com.zhen.MySillyDesktopCatGame.Controller.Command.FireballSpellCommand;
import com.zhen.MySillyDesktopCatGame.Type.SpellSlotType;
import com.zhen.MySillyDesktopCatGame.Type.SpellType;

public class MiniGameShopController {

    private MainController mainController;

    public MiniGameShopController(MainController mainController) {
        this.mainController = mainController;
    }

    public void buyItem(BuyItemAction action)
    {
        System.out.println("Buying Item");
        SpellType spellType = action.getSpellType();
        switch (spellType)
        {
            case FIREBALL:
                System.out.println("Trying to buy fireball");
                if(checkUserHasPointsToBuyItem(spellType))
                {
                    SpellSlotType emptySpellSlotIndex = checkForEmptySpellSlot();
                    if(emptySpellSlotIndex == null)
                    {
                        return;
                    }
                    else
                    {
                        mainController.getSpellController().setSpellCommand(checkForEmptySpellSlot(), new FireballSpellCommand(mainController.getGameState()));
                        mainController.getGameState().setCurrentPoints(mainController.getGameState().getCurrentPoints() - mainController.getGameState().getSpellTypeToSpellMap().get(spellType).getCost());
                        System.out.println(mainController.getSpellController().getSpellCommand()[0]);
                    }
                }
                break;
            case FREEZE:
                break;
            case LIGHTNING:
                break;
            default:
                break;
        }
    }

    public boolean checkUserHasPointsToBuyItem(SpellType spellType)
    {
        int userPoint = mainController.getGameState().getCurrentPoints();
        int spellCost = mainController.getGameState().getSpellTypeToSpellMap().get(spellType).getCost();
        if(userPoint >= spellCost)
        {
            System.out.println("Has Enough Points");
            return true;
        }
        System.out.println("Dont Have Enough Points");
        return false;
    }

    public SpellSlotType checkForEmptySpellSlot()
    {
        Command[] commandList = mainController.getSpellController().getSpellCommand();
        for(int i = 0; i < commandList.length; i++)
        {
            if(commandList[i] == null)
            {
                return SpellSlotType.values()[i];
            }
        }
        return null;
    }
}
