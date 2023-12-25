package Iter;

import javafx.scene.image.Image;

import java.nio.file.Paths;

public class ConcreteAggregate implements Aggregate {
    private String filetopic;

    public ConcreteAggregate(String filetopic) {
        this.filetopic = filetopic;
    }

    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }

    private class ImageIterator implements Iterator {
        private int count = 1;

        private Image getImage(int iterator) {
            String filename = Paths.get("src/main/resources/" + filetopic + iterator + ".jpg").toUri().toString();
            return new Image(filename);
        }

        @Override
        public boolean hasNext() {
            return !getImage(count + 1).isError();
        }
        @Override
        public boolean hasPreview() {
            return !getImage(count - 1).isError();
        }
        @Override
        public Image next() {
            if (hasNext()) return getImage(++count);
            else {
                count = 1;
                return getImage(count);
            }

        }
        @Override
        public Image preview() {
            if(hasPreview()) return getImage(--count);
            else{
                count = 5;
                return getImage(count);
            }
        }
    }
}