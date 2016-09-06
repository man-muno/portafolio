package de.moebius.carassist.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.google.android.glass.widget.CardBuilder;

import de.moebius.carassist.R;
import de.moebius.carassist.model.api.WFActivity;

/**
 * Created by tlamp on 09.01.2015.
 */
public class CardGenerator {

    /**
     * Retruns a view given the type of activity.
     * @param inActivity Activity should contain all the necessary information to return the correct view. Not Null
     * @param context The main activity context. Not Null
     * @return Returns one from 4 types of cards. Column card has image on the left text on the right. Text card with just text. Image on the background and text on the top. Image on the background and text on the bottom.
     */
    public static View getView(WFActivity inActivity, Context context){
        String type = inActivity.getType();
        View response = null;
        switch (type){
            case "column":
                Drawable drawable = context.getResources().getDrawable(context.getResources().getIdentifier(inActivity.getImage(), "drawable", context.getPackageName()));
                response = new CardBuilder(context, CardBuilder.Layout.COLUMNS)
                        .setText(inActivity.getText())
                        .setIcon(drawable)
                        .getView();
                break;
            case "text":
                response = new CardBuilder(context, CardBuilder.Layout.AUTHOR)
                        .setText(inActivity.getText())
                        .getView();
                break;
            case "backgroundTop":
                CardBuilder imageOverlayCard = new CardBuilder(context, CardBuilder.Layout.EMBED_INSIDE).setEmbeddedLayout(R.layout.image_overlay);
                View imageOverlayAboveView = imageOverlayCard.getView();
                Drawable drawableAboveOverlay = context.getResources().getDrawable(context.getResources().getIdentifier(inActivity.getImage(), "drawable", context.getPackageName()));
                imageOverlayAboveView.setBackground(drawableAboveOverlay);
                //Top text
                TextView topText = (TextView) imageOverlayAboveView.findViewById(R.id.topText);
                topText.setText(inActivity.getText());
                //Bottom text
                TextView toHideBottomText = (TextView) imageOverlayAboveView.findViewById(R.id.bottomText);
                toHideBottomText.setText("");
                toHideBottomText.setAlpha(0F);
                response = imageOverlayAboveView;
                break;
            case "backgroundBottom":
                CardBuilder imageOverlayBelowCard = new CardBuilder(context, CardBuilder.Layout.EMBED_INSIDE).setEmbeddedLayout(R.layout.image_overlay);
                View imageOverlayBelowView = imageOverlayBelowCard.getView();
                Drawable drawableOverlayBelow = context.getResources().getDrawable(context.getResources().getIdentifier(inActivity.getImage(), "drawable", context.getPackageName()));
                imageOverlayBelowView.setBackground(drawableOverlayBelow);
                //Top text
                TextView toHideTopText = (TextView) imageOverlayBelowView.findViewById(R.id.topText);
                toHideTopText.setText("");
                toHideTopText.setAlpha(0F);
                //Bottom text
                TextView bottomText = (TextView) imageOverlayBelowView.findViewById(R.id.bottomText);
                bottomText.setText(inActivity.getText());
                response = imageOverlayBelowView;
                break;
            case "image":
                Drawable drawableJustImageBackground = context.getResources().getDrawable(context.getResources().getIdentifier(inActivity.getImage(), "drawable", context.getPackageName()));
                response = new CardBuilder(context, CardBuilder.Layout.CAPTION)
                        .setText(inActivity.getText())
                        .setIcon(drawableJustImageBackground)
                        .getView();
                break;
        }

        return response;
    }
}
