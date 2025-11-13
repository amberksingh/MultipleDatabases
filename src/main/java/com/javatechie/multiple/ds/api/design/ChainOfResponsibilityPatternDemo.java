package com.javatechie.multiple.ds.api.design;

class Number {

    int value;

    Number(int value) {
        this.value = value;
    }
}

abstract class Processor {

    Processor nextProcessor;

    Processor(Processor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    void process(Number number) {
        if (nextProcessor != null)
            nextProcessor.process(number);
    }
}

class NegativeProcessor extends Processor {

    NegativeProcessor(Processor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void process(Number number) {
        if (number.value < 0) {
            System.out.println("NegativeProcessor. Request handled for number : " + number.value);
        } else {
            System.out.println("Unable to handle request in NegativeProcessor. Moving to next");
            super.process(number);
        }
    }
}

class ZeroProcessor extends Processor {

    ZeroProcessor(Processor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void process(Number number) {
        if (number.value == 0) {
            System.out.println("ZeroProcessor. Request handled for number :" + number.value);
        } else {
            System.out.println("Unable to handle request in ZeroProcessor. Moving to next");
            super.process(number);
        }
    }
}

class PositiveProcessor extends Processor {

    PositiveProcessor(Processor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void process(Number number) {
        if (number.value > 0) {
            System.out.println("PositiveProcessor. Request handled for number : " + number.value);
        } else {
            System.out.println("Unable to handle request in PositiveProcessor. Cant handle request");
            super.process(number);
        }
    }
}

class BuildChain {

    Processor processor;

    BuildChain() {
        //
        this.processor = new NegativeProcessor(new ZeroProcessor(new PositiveProcessor(null)));
    }

    void process(Number number) {
        processor.process(number);
    }
}

public class ChainOfResponsibilityPatternDemo {

    public static void main(String[] args) {

        BuildChain buildChain = new BuildChain();
        buildChain.process(new Number(10));
        System.out.println("=====================");

        buildChain.process(new Number(0));
        System.out.println("=====================");

        buildChain.process(new Number(-10));
        System.out.println("=====================");

        buildChain.process(new Number(33));
        System.out.println("=====================");

        buildChain.process(new Number(-300));
        System.out.println("=====================");

        buildChain.process(new Number(0));
        System.out.println("=====================");
        buildChain.process(new Number(60));
    }
}
