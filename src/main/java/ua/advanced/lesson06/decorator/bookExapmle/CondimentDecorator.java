package ua.advanced.lesson06.decorator.bookExapmle;

public abstract class CondimentDecorator extends Beverage {
	Beverage beverage;
	public abstract String getDescription();
}
