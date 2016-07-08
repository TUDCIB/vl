package de.tudresden.bau.cib.ui.widgets.notification.notifier;

import org.eclipse.swt.graphics.Image;

import de.tudresden.bau.cib.ui.widgets.notification.cache.ImageCache;

/**
 * Taken from http://hexapixel.com/2009/06/30/creating-a-notification-popup-widget
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public enum NotificationType {
    ERROR(ImageCache.getImage("error.png")),
    DELETE(ImageCache.getImage("delete.png")),
    WARN(ImageCache.getImage("warn.png")),
    SUCCESS(ImageCache.getImage("ok.png")),
    INFO(ImageCache.getImage("info.png")),
    LIBRARY(ImageCache.getImage("library.png")),
    HINT(ImageCache.getImage("hint.png")),
    PRINTED(ImageCache.getImage("printer.png")),
    CONNECTION_TERMINATED(ImageCache.getImage("terminated.png")),
    CONNECTION_FAILED(ImageCache.getImage("connecting.png")),
    CONNECTED(ImageCache.getImage("connected.png")),
    DISCONNECTED(ImageCache.getImage("disconnected.png")),
    TRANSACTION_OK(ImageCache.getImage("ok.png")),
    TRANSACTION_FAIL(ImageCache.getImage("error.png"));

    private Image _image;

    private NotificationType(Image img) {
        _image = img;
    }

    public Image getImage() {
        return _image;
    }
}
