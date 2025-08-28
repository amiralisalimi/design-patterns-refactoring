# DesignPatternsRefactoring
SE Lab - Assignment 6 - SUT | 14033 
# ریفکتورها و بازآرایی‌های پروژه MiniJava

## Facade – CompilerFacade و Compiler
یک Facade برای Compiler ساخته شد تا دسترسی به Parser و Scanner ساده شود. کاربران دیگر نیازی به ایجاد یا مدیریت مستقیم این کلاس‌ها ندارند و فقط با CompilerFacade کار می‌کنند.

## Strategy – ActionStrategy
برای جایگزینی switch-case در parser، هر نوع Action (Shift, Reduce, Accept) یک کلاس جداگانه با متد execute دارد. این باعث شد اضافه کردن action جدید بدون تغییر در parser ممکن شود و کد خواناتر و قابل تست‌تر شود.

## Separate Query from Modifier
توابعی که داده را query می‌کنند نباید هیچ side-effect یا تغییر وضعیت ایجاد کنند. عملیات تغییر وضعیت و ErrorHandler در توابع جداگانه انجام شد تا عملیات خواندن داده بدون اثر جانبی باشد.

## Self Encapsulated Field
فیلدهای کلاس‌ها private شدند و تمام دسترسی‌ها از طریق getter و setter انجام می‌شود. این کار باعث کنترل بهتر روی فیلدها و امکان اضافه کردن validation می‌شود.

## Null Object – Klass.getField
به جای بازگرداندن null هنگام پیدا نشدن فیلد، یک NullSymbol امن برگردانده می‌شود. این روش جلوی NullPointerException را می‌گیرد و کد کلاینت بدون نیاز به بررسی null، پیش‌بینی‌پذیر است.

## Extract Method – ParseTable
کد parsing جدول parser در constructor به متدهای جداگانه مثل parseRow و parsecol منتقل شد. constructor کوتاه‌تر و خواناتر شد. Magic String ها نیز به صورت ثابت تعریف شدند تا کد خواناتر شود.
